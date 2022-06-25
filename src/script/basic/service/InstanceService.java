package script.basic.service;

public class InstanceService {


    /** INSTANCES **/

    StorageService storageService = new StorageService();


    /** GETTERS **/

    public StorageService getStorageService() {
        return storageService;
    }




    /** CLASS INITIALIZATION **/


    private static InstanceService instanceService;

    public static InstanceService getInstanceService() {
        if(instanceService == null)
            instanceService = new InstanceService();
        return instanceService;
    }
}
