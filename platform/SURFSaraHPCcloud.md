# SURFsara HCPcloud cluster

Information on how to create, start and shutdown Virtual Machines (VMs) at SURFsara HCPcloud.

## Create VM
To create a VM or a set of VMs do as follow:
* Login into: https://ui.hpccloud.surfsara.nl/#vms-tab  
  The login credentials (or account setup) should be requested from the admin for **SURFSara HPCcloud** provider. 

* Then click:  
  Instances -> VMs

* Click the **"+""** to add VMs.

* Selected the [suggested template](https://github.com/eEcoLiDAR/infrastructure/blob/master/platform/README.md#virtual-machines) for **SURFSara HPCcloud**.

* In the box **"VM name"** add the cluster's name. For multiple VMs, click in the question mark logo and follow the instructions. For example, for a cluster called *eEcolidar* with 4 VMs, an user should insert *eEcolidar%i* in the **"VM name"** and select 4 in **"Number of instances"** and then press created. It will create 4 VMs, *eEcolidar0*, *eEcolidar1*, *eEcolidar2*, amd *eEcolidar3*. They will be *Running* once created, so no need to start them.


## Start VM
To start a VM or a set of VMs do as follow:
* Login into: https://ui.hpccloud.surfsara.nl/#vms-tab  
  The login credentials (or account setup) should be requested from the admin for **SURFSara HPCcloud** provider. 

* Then click:  
  Instances -> VMs

* Check the boxes for *eecolidar*, *eecolidar0*, ..., *eecolidarN* and click **Start** button and wait a minute

## Poweroff VM
To poweroff a VM or a set of VMs do as follow:
* Login into: https://ui.hpccloud.surfsara.nl/#vms-tab  
  The login credentials (or account setup) should be requested from the admin for **SURFSara HPCcloud** provider. 

* Then click  
  Instances -> VMs

* Check the boxes for *eecolidar*, *eecolidar0*, ..., *eecolidarN* and under the button *power* click **"PowerOff"**
