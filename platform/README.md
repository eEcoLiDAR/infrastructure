# Platform

All the information in how to manage, up- and down- load data, and interact with platform is here summarized. The deployment of the platform only needs a list of machines and all the rest is taken cared by **Emma**.

Once the platform is up and running, the user only needs to upload the input data to the correct storage layer and then the platform is ready for [application developement](../applications).

## Emma
Emma is a project where ansible is used to setup a Spark cluster with GeoTrellis and SciSpark modules, and using for storage two flavors of storage, as file-based HDFS and GlusterFS and as object-based Minio (it has the same API as Amazon S3). 

**For this project the platform provision should only install a light version of the platform**. Such light platform does not have Docker-swarm and GlusterFS. To install such platform the user instead of running **ansible-playbook install_platform.yml**, as mentioned in [provision section](https://github.com/nlesc-sherlock/emma/blob/eEcoLiDAR/ansible.md#provision), the user should run the following:
```
ansible-playbook install_platform_light.yml --tags "common,minio,hadoop,spark,pdal,geotrellis,jupyterhub,ecolidar"
```
* To use an existing plattform, download the `tar` file from [eEcolidar's SURFsara Grid Storage](https://webdav.grid.surfsara.nl/pnfs/grid.sara.nl/data/projects.nl/eecolidar/01_Work/SURFSara_HPCcloud_Clusters/) with its configuration parameters. To `tar` file is protected with a password, it can be requested from the eEcolidar's admin for the SURFsara HPCcloud.

Cloud provider | Cluster name | tar files'name
--- | --- | --- 
SURF-Sara HPC cloud | eecolidar | SURFSara_HPCcloud_eecolidar.tar.gz

Unzip/UnTar the folder and follow the instructions in its README. Before that we recommend to [install ansible](https://github.com/nlesc-sherlock/emma/blob/master/ansible.md#install-ansible).

* To install the platform the user should read the instructions detailed in [**emma's** set up](https://github.com/nlesc-sherlock/emma/blob/master/README.md#setup-environment). This assumes that you use an Ubuntu machine or that you have Windows 10 with [**WSL**](https://msdn.microsoft.com/en-us/commandline/wsl/install_guide). If not, go to [**emma's** README](https://github.com/nlesc-sherlock/emma/blob/master/README.md).
* To update the **Hadoop** and **Spark** cluster of your platform please follow the instructions in [**emma's update existent platform**](https://github.com/nlesc-sherlock/emma/blob/master/ansible.md#update-an-existing-platform).
* To add new nodes to an existent cluster please read [**emma's add new node**](https://github.com/nlesc-sherlock/emma/blob/master/ansible.md#extend-an-existing-platform). Note that the installation command `ansible-playbook -i hosts_new install_platform_light.yml --skip-tags "hdfs_format"` should be replaced by `ansible-playbook -i hosts_new install_platform_light.yml --tags "common,minio,hadoop,spark,pdal,geotrellis,jupyterhub,ecolidar" --skip-tags "hdfs_format"` to avoid the installation of additional systems not needed by eEcolidar users.
