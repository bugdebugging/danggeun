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

resource "aws_key_pair" "bastion-key-pair" {
  public_key = file("${path.module}/DemoKey.pem")
  key_name = "bastion-key-pair"
}
resource "aws_instance" "bastion_host" {
  ami = "ami-0f66bf23ed74d9284"
  instance_type = "t2.micro"
  subnet_id = var.public_subnet_id
  tags = {
    Name = "bastion host"
  }
  security_groups = [aws_security_group.bastion-security-group.id]
  key_name = aws_key_pair.bastion-key-pair.key_name
}
