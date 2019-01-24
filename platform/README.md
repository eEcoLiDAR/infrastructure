# Platform

All the information in how to manage, up- and down- load data, and interact with platform is here summarized. The deployment of the platform only needs a list of machines and all the rest is taken cared by **Emma**.

Once the platform is up and running, the user only needs to upload the input data to the correct storage layer and then the platform is ready for [application developement](../applications).

## Emma
Emma is a project where ansible is used to install **eEcolidar platform**, i.e., a set of tools necessary for the eEcolidar project.

### New platform
Each platform setup will use a set of Virtual Machines (VMs). Create a set of VMs following the instructions of your preferred cloud provider.

Cloud provider | VM Template name | Instructions
--- | --- | ---
SURF-Sara HPC cloud | Ubuntu-16.04.4-Server (2018-04-01) eEcolidarCluster | [README](https://github.com/eEcoLiDAR/infrastructure/blob/master/platform/HPCcloud.md)

To install the platform the user should read the instructions detailed in [**emma's** set up](https://github.com/nlesc-sherlock/emma/blob/master/README.md#setup-environment). This assumes that you either use an Ubuntu machine or that you have Windows 10 with [**WSL**](https://msdn.microsoft.com/en-us/commandline/wsl/install_guide). If not, go to [**emma's** README](https://github.com/nlesc-sherlock/emma/blob/master/README.md).

**For this project the platform provision should only install a light version of the platform**. To install such platform the user instead of running **ansible-playbook install_platform.yml**, as mentioned in [provision section](https://github.com/nlesc-sherlock/emma/blob/eEcoLiDAR/ansible.md#provision), the user should run the following:
```
ansible-playbook install_platform_light.yml --tags "common,pdal,geotrellis,ecolidar"
```

### Existing platform
To use an existing plattform, download the `zip` file from [eEcolidar's SURFsara Grid Storage](https://webdav.grid.surfsara.nl/pnfs/grid.sara.nl/data/projects.nl/eecolidar/01_Work/SURFSara_HPCcloud_Clusters/) with its configuration parameters. Each `zip` file is protected with a password, it can be requested from the SURFsara HPCcloud account admin. the following table indicates which `zip` name for each cluster.

Cloud provider | Cluster name | tar files'name | VM Template's name
--- | --- | --- 
SURF-Sara HPC cloud | eecolidar | SURFSara_HPCcloud_eecolidar.tar.gz | Ubuntu-16.04.4-Server (2018-04-01) eEcolidarCluster

Extract the `zip` file content to root directory of this repository (if not yet cloned, please follow [the instructions](https://github.com/eEcoLiDAR/infrastructure#infrastructure) to clone it). After extracting its content, follow the instructions in its README. Before that we recommend you to [install ansible](https://github.com/nlesc-sherlock/emma/blob/master/ansible.md#install-ansible).

#### Update existing platform
Often VMs crash and they need to be reconstructed. To either reconstruct crashed VMs or extend an existing cluster and user has first to create the VMs. In case of crashed VMs the user should use the same names. Once they are created, the next step are listed in [**emma's add new node**](https://github.com/nlesc-sherlock/emma/blob/master/ansible.md#extend-an-existing-platform) documentation.

Note that the installation command `ansible-playbook -i hosts_new install_platform_light.yml --skip-tags "hdfs_format"` should be replaced by `ansible-playbook -i hosts_new install_platform_light.yml --tags "common,pdal,geotrellis,ecolidar" --skip-tags "hdfs_format"` to avoid the installation of additional systems not needed by eEcolidar users.

In case it was a cluster extension, the new nodes address needs to be added to the `hosts` file located at `infrastructure/platform/emma/`.
