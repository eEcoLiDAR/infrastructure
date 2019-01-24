# SURFsara HCPcloud cluster

Information on how to create, start and shutdown Virtual Machines (VMs) at SURFsara HCPcloud.

## Create VM

## Start VM
To start a VM or a set of VMs do as follow:
* Login into: https://ui.hpccloud.surfsara.nl/#vms-tab  
  The login credentials are the ones under the cloud provide **SURFSara HPCcloud** in the credentials file included in the requested configuration package 

  * Then click:  
  Instances -> VMs

  * Check the boxes for *eecolidar*, *eecolidar0*, ..., *eecolidarN* and click **Start** button and wait a minute

## Poweroff VM
To poweroff a VM or a set of VMs do as follow:
* Login into: https://ui.hpccloud.surfsara.nl/#vms-tab  
The login credentials should be requested from [cluster's owner/contact person](https://github.com/eEcolidar/infrastructure/tree/master/platform#emma). Use the ones under **SURFSara HPCcloud**.

* Then click  
  Instances -> VMs

  * Check the boxes for *eecolidar*, *eecolidar0*, ..., *eecolidarN* and under the button *power* click **"PowerOff"**


## Update firewall
With all nodes up and running, and services initialized the user is now ready to develop or deploy an application, but before that pay attention to the following:
  * It is important to make sure the user's computer IP is in the list of allowed IPs, if not it is required the following step (**PLEASE BE CAREFUL IN ADDING IPs**). 
    * If you have the IP **123.45.67.87** (just and example) the following entry *123.45.67.0/24* (We replaced *87* by *0/24* and it should always be like that) to ~/infrastructure/platform/emma/vars/common_vars.yml list of IPs and then run:
    ```
    cd ~/infrastructure/emma/
    . env_linux.sh
    ansible playbook playbooks/install_spark.yml --tags "firewall"
    ```
