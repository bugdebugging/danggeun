variable "region" {
}
variable "name" {
}
variable "eks-cluster-name" {
  default = "base-eks-cluster"
}

variable "cidr" {
  description = "The cidr block of vpc"
  default = "10.0.0.0/16"
}
variable "azs" {
  description = "The available zone in vpc."
  default = ["ap-northeast-2a","ap-northeast-2b","ap-northeast-2c"]
}
variable "private_subnets" {
  description = "The private subnets of vpc."
  default = ["10.0.1.0/24", "10.0.2.0/24"]
}
variable "public_subnets" {
  description = "The private subnets of vpc."
  default = ["10.0.101.0/24", "10.0.102.0/24"]
}
