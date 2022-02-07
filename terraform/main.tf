variable "region" {}

variable "name" {}
variable "eks-cluster-name" {}

#rds
variable "engine" {}
variable "engine_version" {}
variable "db_instance_class" {}
variable "allocated_storage" {}
variable "db_user" {}
variable "db_password" {}

#eks
variable "instance_class" {}
variable "min_size" {}
variable "max_size" {}
variable "desired_capacity" {}

#codebuild
variable "git_location" {}
variable "aws_account_id" {}
variable "image_tag" {}

module "vpc" {
  source = "./vpc"
  name = var.name
  region = var.region
  eks-cluster-name = var.eks-cluster-name
}

module "rds" {
  source = "./rds"
  name = var.name
  engine = var.engine
  engine_version = var.engine_version
  instance_class = var.db_instance_class
  vpc_id = module.vpc.vpc_id
  vpc_cidr = module.vpc.vpc_cidr
  db_subnet_group_ids = module.vpc.private_subnet_ids
  allocated_storage = var.allocated_storage
  db_user = var.db_user
  db_password = var.db_password
}

module "ecr" {
  source = "./ecr"
  name = var.name
}

module "s3" {
  source = "./s3"
  name = var.name
}
data "aws_key_pair" "eks-node-key-pair"{
  key_name="DemoKey"
}
module "eks" {
  source = "./eks"
  name = var.eks-cluster-name
  region = var.region
  private_subnet_ids = module.vpc.private_subnet_ids
  public_subnet_ids = module.vpc.public_subnet_ids

  public_key_name = data.aws_key_pair.eks-node-key-pair.key_name
  instance_type = var.instance_class
  min_size = var.min_size
  max_size = var.max_size
  desired_capacity = var.desired_capacity
  filename = "${path.module}/cluster.yaml"
}

module "codebuild" {
  source = "./codebuild"
  region = var.region
  name = var.name
  git_location = var.git_location
  vpc_id = module.vpc.vpc_id
  private_subnet_ids = module.vpc.private_subnet_ids
  security_group_ids = [module.vpc.vpc_default_security_group_id]
  aws_account_id = var.aws_account_id
  image_repo_name = var.name
  image_tag = var.image_tag
  db_url = module.rds.rds_endpoint
  db_user = var.db_user
  db_password = var.db_password
}

module "bastionHost" {
  source = "./bastion"
  public_subnet_id = module.vpc.public_subnet_ids[0]
  vpc_id = module.vpc.vpc_id
}