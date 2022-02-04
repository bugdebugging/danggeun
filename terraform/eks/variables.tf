variable "name" {}
variable "region" {}
variable "private_subnet_ids" {}
variable "public_subnet_ids" {}

variable "public_key_name" {}
variable "instance_type" {}

variable "min_size" {default = 2}
variable "max_size" {default = 4}
variable "desired_capacity" {default = 2}
variable "filename" {}