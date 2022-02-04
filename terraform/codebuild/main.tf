resource "aws_s3_bucket" "codebuild-s3-bucket" {
  bucket = "my-danggeun-codebuild-bucket"
  acl = "private"
}
//ecr,secretmanager,eks
resource "aws_iam_role" "codebuild_role" {
  name = "codebuild_role"
  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": "codebuild.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
EOF
}
resource "aws_iam_role_policy" "codebuild_role_policy-my" {
  policy = <<POLICY
{
  "Version": "2012-10-17",
  "Statement": [
   {
      "Effect": "Allow",
      "Action": [
        "ec2:CreateNetworkInterface",
        "ec2:DescribeDhcpOptions",
        "ec2:DescribeNetworkInterfaces",
        "ec2:DeleteNetworkInterface",
        "ec2:DescribeSubnets",
        "ec2:DescribeSecurityGroups",
        "ec2:DescribeVpcs"
        ],
        "Resource": "*"
    },
    {
      "Sid": "CodeBuildAccessPolicy",
      "Effect": "Allow",
      "Action": [
        "codebuild:*"
      ],
      "Resource": "*"
    },
    {
      "Sid": "CodeBuildRolePolicy",
      "Effect": "Allow",
      "Action": [
        "iam:PassRole"
      ],
      "Resource": "arn:aws:iam::308987748304:role/codebuild_role"
    },
    {
      "Sid": "CloudWatchLogsAccessPolicy",
      "Effect": "Allow",
      "Action": [
        "logs:FilterLogEvents",
        "logs:GetLogEvents"
      ],
      "Resource": "*"
    },
    {
      "Sid": "S3AccessPolicy",
      "Effect": "Allow",
      "Action": [
        "s3:CreateBucket",
        "s3:GetObject",
        "s3:List*",
        "s3:PutObject"
      ],
      "Resource": "*"
    },
    {
      "Sid": "S3BucketIdentity",
      "Effect": "Allow",
      "Action": [
        "s3:GetBucketAcl",
        "s3:GetBucketLocation"
      ],
      "Resource": "*"
    }
  ]
}
  POLICY
  role = aws_iam_role.codebuild_role.id
}


resource "aws_codebuild_project" "danggeun_cicd" {
  name = var.name
  service_role = aws_iam_role.codebuild_role.arn
  artifacts {
    type = "NO_ARTIFACTS"
  }

  environment {
    compute_type = "BUILD_GENERAL1_SMALL"
    image = "aws/codebuild/standard:3.0"
    type = "LINUX_CONTAINER"
    privileged_mode = true

    environment_variable {
      type = "PLAINTEXT"
      name = "AWS_DEFAULT_REGION"
      value = var.region
    }
    environment_variable {
      type = "PLAINTEXT"
      name = "AWS_ACCOUNT_ID"
      value = var.aws_account_id
    }
    environment_variable {
      type = "PLAINTEXT"
      name = "IMAGE_REPO_NAME"
      value = var.image_repo_name
    }
    environment_variable {
      type = "PLAINTEXT"
      name = "IMAGE_TAG"
      value = var.image_tag
    }
    #for db
    environment_variable {
      type = "PLAINTEXT"
      name = "DB_URL"
      value = var.db_url
    }
    environment_variable {
      type = "PLAINTEXT"
      name = "DB_USER"
      value = var.db_user
    }
    environment_variable {
      type = "PLAINTEXT"
      name = "DB_PASSWORD"
      value = var.db_password
    }
  }

  source {
    type = "GITHUB"
    location = var.git_location
    git_clone_depth = 1
  }
  source_version = "main"

  vpc_config {
    security_group_ids = var.security_group_ids
    subnets = var.public_subnet_ids
    vpc_id = var.vpc_id
  }
}

resource "aws_codebuild_webhook" "danggeun-codebuild-webhook" {
  project_name = aws_codebuild_project.danggeun_cicd.name
  build_type   = "BUILD"
  filter_group {
    filter {
      type    = "EVENT"
      pattern = "PUSH"
    }

    filter {
      type    = "HEAD_REF"
      pattern = "main"
    }
  }
}
