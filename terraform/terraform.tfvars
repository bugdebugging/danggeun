region = "ap-northeast-2"

name="danggeun"
eks-cluster-name = "daggeun-eks-cluster"

# rds
engine = "mysql"
engine_version = "8.0.11"
db_instance_class = "db.t3.small"
allocated_storage = 15

#eks
instance_class = "t3.small"
min_size = 2
max_size = 4
desired_capacity = 2

#codebuild
git_location = "https://github.com/bugdebugging/danggeun.git"
aws_account_id = "308987748304"
image_tag = "daggeun-backend"