module "vpc" {
  source = "terraform-aws-modules/vpc/aws"

  name = var.name
  cidr = var.cidr

  azs = var.azs
  private_subnets = var.private_subnets
  public_subnets = var.public_subnets

  enable_nat_gateway = true
  enable_dns_hostnames = true
  single_nat_gateway = true

  tags = {
    "kubernetes.io/cluster/${var.eks-cluster-name}" = "shared"
  }
  public_subnet_tags = {
    "kubernetes.io/cluster/${var.eks-cluster-name}" = "shared"
    "kubernetes.io/role/elb" = "1"
  }
  private_subnet_tags = {
    "kubernetes.io/cluster/${var.eks-cluster-name}" = "shared"
    "kubernetes.io/role/internal-elb" = "1"
  }
}