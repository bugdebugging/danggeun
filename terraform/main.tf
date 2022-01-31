module "vpc" {
  source = "./vpc"
  name = var.name
  region = var.region
  eks-cluster-name = var.eks-cluster-name
}