variable "rds_name" {}
variable "engine" {
  default = "mysql"
}
variable "engine_version" {
  default = 8.0.11
}

variable "instance_class" {
  default = "db.t3.small"
}
variable "db_user" {
  description = "user of rds"
  type = string
}
variable "db_password" {
  description = "password of rds"
  type = string
}
variable "allocated_storage" {
  type = number
  default = 15
}