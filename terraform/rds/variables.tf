variable "engine" {
  default = "mysql"
}
variable "instance_class" {
  default = "db.t3.micro"
}
variable "vpc_id" {
}
variable "db_subnet_group_ids" {
}
variable "name" {}

variable "engine_version" {
}
variable "allocated_storage" {}
variable "db_user" {}
variable "db_password" {}