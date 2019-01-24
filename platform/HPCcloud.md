# SURFsara HCPcloud cluster

Information on how to start and shutdown the platform at SURFsara HCPcloud. It is recommended that first you request configuration package for **SURFsara HCPcloud cluster** from [cluster's owner/contact person](https://github.com/eEcolidar/infrastructure/blob/master/platform/README.md#emma).

* Start all the nodes required for the platform
  * Login into: https://ui.hpccloud.surfsara.nl/#vms-tab  
  The login credentials are the ones under the **HPC_ACCOUNT** in the credentials file included in the requested configuration package 

  * Then click:  
  Instances -> VMs

  * Check the boxes for eecolidar, eecolidar0, ..., eecolidarN
  Click start button and wait a minute

* Start platform  
  ```
  cd ~/infrastructure/emma/
  . env_linux.sh
  ansible-playbook start_platform.yml
  ```
  
  * Start a specific service
  ```
  #Only Spark and Jupythub
  ansible-playbook start_platform.yml --tags "spark,jupyterhub"
  
  #All except Spark
  ansible-playbook start_platform.yml --skip-tags "spark"
  ```

* Shutdown platform
  ```
  cd ~/infrastructure/emma/
  . env_linux.sh
  ansible-playbook shutdown_platform.yml
  ```
  
  * Specific service: Examples to be combined with the ones above.
  ```
  #Only Spark and Jupythub
  ansible-playbook shutdown_platform.yml --tags "spark,jupyterhub"
  
  #All except Spark
  ansible-playbook shutdown_platform.yml --skip-tags "spark"
  ```

* ShutDown all machines
  * Login into: https://ui.hpccloud.surfsara.nl/#vms-tab  
  The login credentials should be requested from [cluster's owner/contact person](https://github.com/eEcolidar/infrastructure/tree/master/platform#emma). Use the ones under **HPC_ACCOUNT**.

  * Then click  
  Instances -> VMs

  * Check the boxes for eecolidar, eecolidar0, ..., eecolidarN and under the button **power** click "PowerOff"

* Extra information:
  * The command to set the environment **.env_linux.sh** only needs to be called once per session.
  
* With all nodes up and running, and services initialized the user is now ready to develop or deploy an application, but before that pay attention to the following:
  * It is important to make sure the user's computer IP is in the list of allowed IPs, if not it is required the following step (**PLEASE BE CAREFUL IN ADDING IPs**). 
    * If you have the IP **123.45.67.87** (just and example) the following entry *123.45.67.0/24* (We replaced *87* by *0/24* and it should always be like that) to ~/infrastructure/emma/vars/common_vars.yml list of IPs and then run:
    ```
    cd ~/infrastructure/emma/
    . env_linux.sh
    ansible playbook playbooks/install_spark.yml --tags "firewall"
    ```
  * It might be necessary to [upload data](https://github.com/eEcolidar/infrastructure/blob/master/platform/README.md#data-loading).
  * If all of above is satisfied, go to the JupyterHub portal (Link available in the *README* file of the configuration package; and credentials available under **JUPYTERHUB_ACCOUNT** in the *credentials* file).  
  * For more information on how to develop applications go to [applications](https://github.com/eEcolidar/infrastructure/tree/fix_documentation/applications).  
