locals {
  cluster_config_vars = {
    cluster_name = var.name
    region = var.region
    public_key_name = var.public_key_name
    instance_type = var.instance_type
    min_size = var.min_size
    max_size = var.max_size
    desired_capacity = var.desired_capacity

    subnet_private={
      private0=var.private_subnet_ids[0],
      private1=var.private_subnet_ids[1],
    }

    subnet_public={
      public0=var.public_subnet_ids[0],
      public1=var.public_subnet_ids[1]
    }
  }
  cluster_config_values = templatefile("${path.module}/cluster.yaml.tmpl", local.cluster_config_vars )
}