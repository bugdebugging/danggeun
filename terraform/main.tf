module "vpc" {
  source = "./vpc"
  name = var.name
  region = var.region
}