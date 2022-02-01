resource "aws_db_subnet_group" "db_private_subnet_group" {
  subnet_ids = var.db_subnet_group_ids
  name = "rds_subnet_group"
}
resource "aws_db_instance" "rds_instance" {
  name = var.name
  engine = var.engine
  engine_version = var.engine_version
  instance_class = var.instance_class
  vpc_security_group_ids = [var.vpc_id]
  db_subnet_group_name = aws_db_subnet_group.db_private_subnet_group.name
  tags = {
    Name=var.name
  }
  allocated_storage = var.allocated_storage
  skip_final_snapshot = true
  username = var.db_user
  password = var.db_password
}
