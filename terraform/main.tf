variable "region" {}

variable "name" {}
variable "eks-cluster-name" {}

variable "engine" {}
variable "engine_version" {}
variable "instance_class" {}
variable "allocated_storage" {}
variable "db_user" {}
variable "db_password" {}

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
  instance_class = var.instance_class
  vpc_id = module.vpc.vpc_id
  db_subnet_group_ids = module.vpc.private_subnet_ids
  allocated_storage = var.allocated_storage
  db_user = var.db_user
  db_password = var.db_password
}