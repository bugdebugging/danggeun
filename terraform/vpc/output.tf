output "private_subnet_ids" {
  value = module.vpc.private_subnets
}
output "public_subnet_ids" {
  value = module.vpc.public_subnets
}