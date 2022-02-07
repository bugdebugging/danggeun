resource "aws_db_subnet_group" "db_private_subnet_group" {
  subnet_ids = var.db_subnet_group_ids
  name = "private_subnet_group"
}
resource "aws_security_group" "rds_security_group" {
  name = "db_security_group"
}
resource "aws_security_group_rule" "rds_security_group_ingress_rule" {
  from_port = 3306
  protocol = "tcp"
  security_group_id = aws_security_group.rds_security_group.id
  to_port = 3306
  type = "ingress"
  cidr_blocks = [var.vpc_cidr]
}
resource "aws_security_group_rule" "rds_security_group_egress_rule" {
  from_port = 0
  protocol = "-1"
  security_group_id = aws_security_group.rds_security_group.id
  to_port = 0
  type = "egress"
  cidr_blocks = ["0.0.0.0/0"]
}

resource "aws_db_instance" "rds_instance" {
  name = var.name
  engine = var.engine
  engine_version = var.engine_version
  instance_class = var.instance_class
  db_subnet_group_name = aws_db_subnet_group.db_private_subnet_group.name
  tags = {
    Name=var.name
  }
  allocated_storage = var.allocated_storage
  skip_final_snapshot = true
  username = var.db_user
  password = var.db_password
}
