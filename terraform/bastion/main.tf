resource "aws_security_group" "bastion-security-group" {
  name = "bastion-security-group"
  vpc_id = var.vpc_id
  ingress {
    from_port = 22
    protocol = "tcp"
    to_port = 22
    cidr_blocks = [
      "0.0.0.0/0"]
  }
  egress {
    from_port = 0
    protocol = "-1"
    to_port = 0
    cidr_blocks = [
      "0.0.0.0/0"]
  }
}

data "aws_key_pair" "bastion-key-pair"{
  key_name="DemoKey"
}
resource "aws_instance" "bastion_host" {
  ami = "ami-0f66bf23ed74d9284"
  instance_type = "t2.micro"
  subnet_id = var.public_subnet_id
  tags = {
    Name = "bastion host"
  }
  security_groups = [aws_security_group.bastion-security-group.id,var.vpc_default_security_group_id]
  key_name = data.aws_key_pair.bastion-key-pair.key_name
}

