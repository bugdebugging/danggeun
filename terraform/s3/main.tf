resource "aws_s3_bucket" "s3" {
  bucket = var.name
  acl = "public-read"
  tags = {
    Name=var.name
  }
}