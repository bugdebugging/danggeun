resource "local_file" "cluster_template"{
  content = local.cluster_config_values
  filename = var.filename
}