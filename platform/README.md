# Platform

All the information in how to manage, up- and down- load data, and interact with platform is here summarized. The deployment of the platform only needs a list of machines and all the rest is taken cared by **Emma**.

## Virtual Machines
Each platform setup will use a set of Virtual Machines (VMs). Create a set of VMs following the instructions of your preferred cloud provider. Please use the suggested template.

Cloud provider | Admin | Suggested VM Template | Instructions
--- | --- | --- | ---
SURFSara HPCcloud | Christiaan Meijer | Ubuntu-16.04.4-Server (2018-04-01) eEcolidarCluster | [README](https://github.com/eEcoLiDAR/infrastructure/blob/master/platform/SURFSaraHPCcloud.md)

## Emma
Emma is a project where ansible is used to install **eEcolidar platform**, i.e., a set of tools necessary for the eEcolidar project.

### New platform
To install the platform the user should read the instructions detailed in [**emma's** set up](https://github.com/nlesc-sherlock/emma/blob/master/README.md#setup-environment). This assumes that you either use an Ubuntu machine or that you have Windows 10 with [**WSL**](https://msdn.microsoft.com/en-us/commandline/wsl/install_guide). If not, go to [**emma's** README](https://github.com/nlesc-sherlock/emma/blob/master/README.md). NOTE: the ssh-key is this case provided by the admin of eEcolidar account at each cloud provider. In the request mention which template will be used.

**For this project the platform provision should only install a light version of the platform**. To install such platform the user instead of running **ansible-playbook install_platform.yml**, as mentioned in [provision section](https://github.com/nlesc-sherlock/emma/blob/eEcoLiDAR/ansible.md#provision), the user should run the following:
```
ansible-playbook install_platform_light.yml --tags "common,pdal,geotrellis,ecolidar"
```

Once it is installed the user only needs to `start the VMs` and once the work is done `shut them down`, detailed instructions are in [HPCcloud.md](https://github.com/eEcoLiDAR/infrastructure/blob/master/platform/HPCcloud.md).


### Existing platform
To use an existing platform, download the `zip` file from [eEcolidar's SURFsara Grid Storage](https://webdav.grid.surfsara.nl/pnfs/grid.sara.nl/data/projects.nl/eecolidar/01_Work/SURFSara_HPCcloud_Clusters/) with its configuration parameters. Each `zip` file is protected with a password, it can be requested from the SURFsara HPCcloud account admin. the following table indicates which `zip` name for each cluster.

Cloud provider | Cluster name | tar files'name | VM Template's used
--- | --- | --- | ---
SURFSara HPCcloud | eecolidar | SURFSara_HPCcloud_eecolidar.zip | Ubuntu-16.04.4-Server (2018-04-01) eEcolidarCluster

Extract the `zip` file content to root directory of this repository (if not yet cloned, please follow [the instructions](https://github.com/eEcoLiDAR/infrastructure#infrastructure) to clone it). After extracting its content, follow the instructions in its README. Before that we recommend you to [install ansible](https://github.com/nlesc-sherlock/emma/blob/master/ansible.md#install-ansible).

#### Use existing platform
Once the configuration parameters are extracted and copied the user only needs to `start the VMs` and once the work is done `shut them down`, detailed instructions are in [HPCcloud.md](https://github.com/eEcoLiDAR/infrastructure/blob/master/platform/HPCcloud.md).

#### Update existing platform
Often VMs crash and they need to be reconstructed. To either reconstruct crashed VMs or extend an existing cluster and user has first to create the VMs. Follow the instructions on how to create [Virtual Machines] for your preferred cloud provider, however, use the template used for the cluster you are fixing or updating.

In case of crashed VMs the user should use the same names. Once they are created, the next step are listed in [**emma's add new node**](https://github.com/nlesc-sherlock/emma/blob/master/ansible.md#extend-an-existing-platform) documentation.

Note that the installation command `ansible-playbook -i hosts_new install_platform_light.yml --skip-tags "hdfs_format"` should be replaced by `ansible-playbook -i hosts_new install_platform_light.yml --tags "common,pdal,geotrellis,ecolidar" --skip-tags "hdfs_format"` to avoid the installation of additional systems not needed by eEcolidar users.

In case it was a cluster extension, the new nodes address needs to be added to the `hosts` file located at `infrastructure/platform/emma/`. Once the configuration parameters are extracted and copied the user only needs to `start the VMs` and once the work is done `shut them down`, detailed instructions are in [HPCcloud.md](https://github.com/eEcoLiDAR/infrastructure/blob/master/platform/HPCcloud.md).

### Update firewall
With all nodes up and running, and services initialized the user is now ready to develop or deploy an application, but before that pay attention to the following:
  * It is important to make sure the user's computer IP is in the list of allowed IPs, if not it is required to update the list of allowed IPs (**PLEASE BE CAREFUL IN ADDING IPs**). If you have the IP **123.45.67.87** (just and example) the following entry *123.45.67.0/24* (We replaced *87* by *0/24* and it should always be like that) to **~/infrastructure/platform/emma/vars/common_vars.yml** list of IPs and then run:
    ```
    cd ~/infrastructure/emma/
    . env_linux.sh
    ansible playbook playbooks/install_spark.yml --tags "firewall"
    ```
