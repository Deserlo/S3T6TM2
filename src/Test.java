
public class Test{

	public static void main(String[] args) {
		
		System.out.println("Testing adding new manager..");
		UserAccount newuser1 = new UserAccount("mgr", "testmgr1", "password");
		newuser1.addNewAccount(newuser1);
		System.out.println("");
			
		System.out.println("Testing adding new developer with valid manager ID..");
		int validMgrID = 1; //assumes developer knows their manager ID aka Team#
		UserAccount newuser2 = new UserAccount("dev", "testDev1", "password", validMgrID);
		newuser2.addNewAccount(newuser2);
		System.out.println("");
		
		System.out.println("Testing adding new developer with invalid manager ID..");
		int invalidMgrID = 444; //assumes developer knows their manager ID aka Team#
		UserAccount newuser3 = new UserAccount("dev", "testDev2", "password", invalidMgrID);
		newuser3.addNewAccount(newuser3);
		System.out.println("");
		

	}

}
