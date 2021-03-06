import java.util.Scanner;

class Main {
	public static Scanner input = new Scanner(System.in);
	public static void initSystem(int maxPeople, String[] mPeople) {
		for (int i = 0; i < maxPeople; i++) {
			mPeople[i] = "";
		}
	}
	public static int newTotalEmployees(int maxPeople, String[] mEmployee) {
		int j = 0;
		for(int i = 0; i < maxPeople; i++) {
			if(!mEmployee[i].trim().equals("")) j++;
		}
		return j;
	}
	public static int getNewId(int[] idsEmployee, int maxPeople) {
		for (int i = 0; i < maxPeople; i++) {
			if(idsEmployee[i] == 0) {
				return i;
			}
		}
		return -1;
	}
	public static String getNewIdSyndicate(int maxPeople, int[] idsSyndicate) {
		for (int i = maxPeople - 1; i >= 0; i--) {
			if(idsSyndicate[i] == 0) {
				String idReturn = String.format("%d", i);
				return idReturn;
			}
		}
		String idReturn = String.format("%d", -1);
		return idReturn;
	}
	public static int verifyId(int maxPeople, int[] idsEmployee, int idEmployee) {
		if(idEmployee >= maxPeople) return 0;
		else if(idsEmployee[idEmployee] == 0) return 0;
		else return 1;
	}
	public static void newEmployee(int maxPeople, String[] mEmployee, int[] idsEmployee, int[] idsSyndicate, String[][] undoEmployee, int[][] undoIdsEmployee, int[][] undoIdsSyndicate, int[] undoVerify, int[] redoVerify) {
		int idEmployee;
		String name = "";
		String address = "";
		String typeEmployee = "";
		String paymentSchedule = "";
		String paymentMethod = "Bank";
		int salary;
		int syndicate = 0;
		int idSyndicate = -1;
		int syndicateRate = 0;
		int ratePlus = 0;
		int optionType = 0;
		int confirm;

		idEmployee = getNewId(idsEmployee, maxPeople);
		System.out.print("\nName: ");
		name = input.nextLine();
		name = input.nextLine();
		System.out.print("Address: ");
		address = input.nextLine();

		do {
			System.out.println("\n[1] - Commissioned");
			System.out.println("[2] - Salaried");
			System.out.println("[3] - Hourly");
			System.out.print("T. Employee: ");
			optionType = input.nextInt();

			if(optionType == 1) {
				typeEmployee = "Commissioned";
				paymentSchedule = "Bi-semanalmente";
			} else if(optionType == 2) {
				typeEmployee = "Salaried";
				paymentSchedule = "Mensalmente";
			} else if(optionType == 3) {
				typeEmployee = "Hourly";
				paymentSchedule = "Semanalmente";
			} else optionType = 0;
		} while (optionType == 0);

		System.out.print("Salary: ");
		salary = input.nextInt();

		System.out.printf("\nID: %d\n", idEmployee);
		System.out.printf("Name: %s\n", name);
		System.out.printf("Address: %s\n", address);
		System.out.printf("T. Employee: %s\n", typeEmployee);
		System.out.printf("P. Schedule : %s\n", paymentSchedule);
		System.out.printf("P. Method: %s\n", paymentMethod);
		System.out.printf("Salary: %d\n", salary);
		System.out.printf("Syndicate: %d\n", syndicate);
		System.out.print("\nConfirm[1/0]: ");
		confirm = input.nextInt();

		if(confirm == 1) {
			access(maxPeople, mEmployee, idsEmployee, idsSyndicate, undoEmployee, undoIdsEmployee, undoIdsSyndicate, undoVerify, redoVerify);
			String Temp1 = String.format("<idEmployee>=%d<name>=%s<address>=%s<typeEmployee>=%s", idEmployee, name, address, typeEmployee);
			String Temp2 = String.format("<paymentSchedule>=%s<paymentMethod>=%s<salary>=%d", paymentSchedule, paymentMethod, salary);
			String Temp3 = String.format("<syndicate>=%d<idSyndicate>=%d<syndicateRate>=%d<ratePlus>=%d<>", syndicate, idSyndicate, syndicateRate, ratePlus);
			String employee = String.format("%s%s%s", Temp1, Temp2, Temp3);
			mEmployee[idEmployee] = employee;
			idsEmployee[idEmployee] = 1;
			System.out.println("Registered employee");
		} else {
			System.out.println("Unregistered employee");
		}
	}
	public static void editEmployee(int maxPeople, String[] mEmployee, int[] idsEmployee, int[] idsSyndicate, String[][] undoEmployee, int[][] undoIdsEmployee, int[][] undoIdsSyndicate, int[] undoVerify, int[] redoVerify) {
		System.out.print("\nEmployee ID: ");
		int idEmployee = input.nextInt();
		String name = "";
		String address = "";
		String typeEmployee = "";
		String paymentSchedule = "";
		String paymentMethod = "";
		String salary = "";
		String syndicate = "";
		String idSyndicate = "";
		String syndicateRate = "";
		String ratePlus = "";

		char[] strAux1 = new char[256];
		char tEquality = 0;
		int menu;

		if(verifyId(maxPeople, idsEmployee, idEmployee) != 1) {
			System.out.println("Employee not found");
			return;
		}

		int sizeEmployee = mEmployee[idEmployee].length();
		for (int i = 0, j = 0; i < sizeEmployee; i++) {
			if(mEmployee[idEmployee].charAt(i) == '=') {
				i += 1;
				while (mEmployee[idEmployee].charAt(i) != '<') {
					strAux1[j++] = mEmployee[idEmployee].charAt(i++);
				}
				for (int k = j; k < strAux1.length; k++) strAux1[k] = ' ';
				String strAux2 = String.copyValueOf(strAux1);
				String strAux3 = strAux2.replaceAll("  ", "");
				String strAux4 = strAux3.replaceAll(" <", "<");
				if(tEquality == 1) name = strAux4;
				if(tEquality == 2) address = strAux4;
				if(tEquality == 3) typeEmployee = strAux4;
				if(tEquality == 4) paymentSchedule = strAux4;
				if(tEquality == 5) paymentMethod = strAux4;
				if(tEquality == 6) salary = strAux4;
				if(tEquality == 7) syndicate = strAux4;
				if(tEquality == 8) idSyndicate = strAux4;
				if(tEquality == 9) syndicateRate = strAux4;
				if(tEquality == 10) ratePlus = strAux4;
				tEquality += 1;
				j = 0;
			}
		}

		do {
			System.out.printf("Employee ID: %d\n", idEmployee);
			if(syndicate.charAt(0) == '1') {
				System.out.printf("Syndicate ID: %s\n", idSyndicate);
			}
			System.out.printf("[2] - Name: %s\n", name);
			System.out.printf("[3] - Address: %s\n", address);
			System.out.printf("[4] - T. Employee: %s\n", typeEmployee);
			System.out.printf("[5] - P. Schedule: %s\n", paymentSchedule);
			System.out.printf("[6] - P. Method: %s\n", paymentMethod);
			System.out.printf("[7] - Salary: %s\n", salary);
			System.out.printf("[8] - Syndicate: %s\n", syndicate);
			if(syndicate.charAt(0) == '1') {
				System.out.printf("[9] - Syndicate Rate: %s\n", syndicateRate);
				System.out.printf("[10] - Syn. Rate Plus: %s\n", ratePlus);
			}

			System.out.println("\n[1] - Save Editing");
			System.out.println("[0] - Cancel Editing");
			System.out.print("Select option: ");
			menu = input.nextInt();

			if(menu == 2) {
				System.out.print("\nName: ");
				name = input.nextLine();
				name = input.nextLine();

			}
			if(menu == 3) {
				System.out.print("\nAddress: ");
				address = input.nextLine();
				address = input.nextLine();

			}
			if(menu == 4) {
				System.out.print("\nT. Employee: ");
				typeEmployee = input.nextLine();
				typeEmployee = input.nextLine();

			}
			if(menu == 5) {
				System.out.print("\nP. Schedule: ");
				paymentSchedule = input.nextLine();
				paymentSchedule = input.nextLine();

			}
			if(menu == 6) {
				System.out.print("\nP. Method: ");
				paymentMethod = input.nextLine();
				paymentMethod = input.nextLine();

			}
			if(menu == 7) {
				System.out.print("\nSalary: ");
				salary = input.nextLine();
				salary = input.nextLine();

			}
			if(menu == 8) {
				System.out.println("\nSyndicate: ");
				if(syndicate.charAt(0) != '1') {
					System.out.println("[1] - Enter");
					System.out.println("[0] - Cancel");
					System.out.print("Select option: ");
					int option = input.nextInt();
					if(option == 1) {
						syndicate = "1";
						idSyndicate = getNewIdSyndicate(maxPeople, idsSyndicate);
						syndicateRate = "25";
					}
				}
				else {
					System.out.println("Only after one year");
				}
			}
			if(menu == 9 && syndicate.charAt(0) == '1') {
				System.out.print("\nSyndicate Rate: ");
				syndicateRate = input.nextLine();
				syndicateRate = input.nextLine();
			}
			if(menu == 10 && syndicate.charAt(0) == '1') {
				System.out.print("\nSyn. Rate Plus: ");
				ratePlus = input.nextLine();
				ratePlus = input.nextLine();
			}
			if(menu == 1) {
				access(maxPeople, mEmployee, idsEmployee, idsSyndicate, undoEmployee, undoIdsEmployee, undoIdsSyndicate, undoVerify, redoVerify);
				String Temp1 = String.format("<idEmployee>=%d<name>=%s<address>=%s<typeEmployee>=%s", idEmployee, name, address, typeEmployee);
				String Temp2 = String.format("<paymentSchedule>=%s<paymentMethod>=%s<salary>=%s", paymentSchedule, paymentMethod, salary);
				String Temp3 = String.format("<syndicate>=%s<idSyndicate>=%s<syndicateRate>=%s<ratePlus>=%s<>", syndicate, idSyndicate, syndicateRate, ratePlus);
				String employee = String.format("%s%s%s", Temp1, Temp2, Temp3);
				mEmployee[idEmployee] = employee;
				if(syndicate.charAt(0) == '1') {
					int i = Integer.parseInt(idSyndicate);
					idsSyndicate[i] = 1;
				}
				System.out.println("Sucess");
				return;
			}
			if(menu == 0) {
				System.out.println("Canceled");
			}
		} while (menu != 0);
	}
	public static void removeEmployee(int maxPeople, String[] mEmployee, int[] idsEmployee, int[] idsSyndicate, String[][] undoEmployee, int[][] undoIdsEmployee, int[][] undoIdsSyndicate, int[] undoVerify, int[] redoVerify) {
		System.out.print("\nEmployee ID: ");
		int idEmployee = input.nextInt();
		String name = "";
		String address = "";
		String typeEmployee = "";
		String paymentSchedule = "";
		String paymentMethod = "";
		String salary = "";
		String syndicate = "";
		String idSyndicate = "";
		String syndicateRate = "";
		String ratePlus = "";
		int confirm;

		char[] strAux1 = new char[256];
		char tEquality = 0;
		int menu;

		if(verifyId(maxPeople, idsEmployee, idEmployee) != 1) {
			System.out.println("Employee not found");
			return;
		}


		int sizeEmployee = mEmployee[idEmployee].length();
		for (int i = 0, j = 0; i < sizeEmployee; i++) {
			if(mEmployee[idEmployee].charAt(i) == '=') {
				i += 1;
				while (mEmployee[idEmployee].charAt(i) != '<') {
					strAux1[j++] = mEmployee[idEmployee].charAt(i++);
				}
				for (int k = j; k < strAux1.length; k++) strAux1[k] = ' ';
				String strAux2 = String.copyValueOf(strAux1);
				String strAux3 = strAux2.replaceAll("  ", "");
				String strAux4 = strAux3.replaceAll(" <", "<");
				if(tEquality == 1) name = strAux4;
				if(tEquality == 2) address = strAux4;
				if(tEquality == 3) typeEmployee = strAux4;
				if(tEquality == 4) paymentSchedule = strAux4;
				if(tEquality == 5) paymentMethod = strAux4;
				if(tEquality == 6) salary = strAux4;
				if(tEquality == 7) syndicate = strAux4;
				if(tEquality == 8) idSyndicate = strAux4;
				if(tEquality == 9) syndicateRate = strAux4;
				if(tEquality == 10) ratePlus = strAux4;
				tEquality += 1;
				j = 0;
			}
		}

		System.out.printf("\nID Employee: %d\n", idEmployee);
		if(syndicate.charAt(0) == '1') {
			System.out.printf("ID Syndicate: %s\n", idSyndicate);
		}
		System.out.printf("Name: %s\n", name);
		System.out.printf("Address: %s\n", address);
		System.out.printf("T. Employee: %s\n", typeEmployee);
		System.out.printf("P. Schedule: %s\n", paymentSchedule);
		System.out.printf("P. Method: %s\n", paymentMethod);
		System.out.printf("Salary: %s\n", salary);
		System.out.printf("Syndicate: %s\n", syndicate);
		if(syndicate.charAt(0) == '1') {
			System.out.printf("Syndicate Rate: %s\n", syndicateRate);
			System.out.printf("Syn. Rate Plus: %s\n", ratePlus);
		}
		System.out.print("\nConfirm[1/0]: ");
		confirm = input.nextInt();

		if(confirm == 1) {
			access(maxPeople, mEmployee, idsEmployee, idsSyndicate, undoEmployee, undoIdsEmployee, undoIdsSyndicate, undoVerify, redoVerify);
			String employee = String.format("%s", "");
			int i = Integer.parseInt(idSyndicate);
			idsSyndicate[i] = 0;
			idsEmployee[idEmployee] = 0;
			mEmployee[idEmployee] = employee;
			System.out.println("Sucess");
		} else {
			System.out.println("Canceled");
		}
	}
	public static void insertFee(int maxPeople, String[] mEmployee, int[] idsEmployee, int[] idsSyndicate, String[][] undoEmployee, int[][] undoIdsEmployee, int[][] undoIdsSyndicate, int[] undoVerify, int[] redoVerify) {
		System.out.print("\nEmployee ID: ");
		int idEmployee = input.nextInt();
		String name = "";
		String address = "";
		String typeEmployee = "";
		String paymentSchedule = "";
		String paymentMethod = "";
		String salary = "";
		String syndicate = "";
		String idSyndicate = "";
		String syndicateRate = "";
		String ratePlus = "";

		char[] strAux1 = new char[256];
		char tEquality = 0;

		if(verifyId(maxPeople, idsEmployee, idEmployee) != 1) {
			System.out.println("Employee not found");
			return;
		}

		int sizeEmployee = mEmployee[idEmployee].length();
		for (int i = 0, j = 0; i < sizeEmployee; i++) {
			if(mEmployee[idEmployee].charAt(i) == '=') {
				i += 1;
				while (mEmployee[idEmployee].charAt(i) != '<') {
					strAux1[j++] = mEmployee[idEmployee].charAt(i++);
				}
				for (int k = j; k < strAux1.length; k++) strAux1[k] = ' ';
				String strAux2 = String.copyValueOf(strAux1);
				String strAux3 = strAux2.replaceAll("  ", "");
				String strAux4 = strAux3.replaceAll(" <", "<");
				if(tEquality == 1) name = strAux4;
				if(tEquality == 2) address = strAux4;
				if(tEquality == 3) typeEmployee = strAux4;
				if(tEquality == 4) paymentSchedule = strAux4;
				if(tEquality == 5) paymentMethod = strAux4;
				if(tEquality == 6) salary = strAux4;
				if(tEquality == 7) syndicate = strAux4;
				if(tEquality == 8) idSyndicate = strAux4;
				if(tEquality == 9) syndicateRate = strAux4;
				if(tEquality == 10) ratePlus = strAux4;
				tEquality += 1;
				j = 0;
			}
		}

		System.out.printf("Employee ID: %d\n", idEmployee);
		if(syndicate.charAt(0) == '1') {
			System.out.printf("Syndicate ID: %s\n", idSyndicate);
		}
		System.out.printf("Name: %s\n", name);
		System.out.printf("Address: %s\n", address);
		System.out.printf("T. Employee: %s\n", typeEmployee);
		System.out.printf("P. Schedule: %s\n", paymentSchedule);
		System.out.printf("P. Method: %s\n", paymentMethod);
		System.out.printf("Salary: %s\n", salary);
		System.out.printf("Syndicate: %s\n", syndicate);
		if(syndicate.charAt(0) == '1') {
			System.out.printf("Syndicate Rate: %s\n", syndicateRate);
			System.out.printf("Syn. Rate Plus: %s\n", ratePlus);
		}

		if(syndicate.charAt(0) != '1') {
			System.out.println("The employee dos not participate in the syndicate");
			return;
		}

		System.out.println("Insert New Rate: ");
		int fee = input.nextInt();

		access(maxPeople, mEmployee, idsEmployee, idsSyndicate, undoEmployee, undoIdsEmployee, undoIdsSyndicate, undoVerify, redoVerify);
		String Temp1 = String.format("<idEmployee>=%d<name>=%s<address>=%s<typeEmployee>=%s", idEmployee, name, address, typeEmployee);
		String Temp2 = String.format("<paymentSchedule>=%s<paymentMethod>=%s<salary>=%s", paymentSchedule, paymentMethod, salary);
		String Temp3 = String.format("<syndicate>=%s<idSyndicate>=%s<syndicateRate>=%s<ratePlus>=%d<>", syndicate, idSyndicate, syndicateRate, fee);
		String employee = String.format("%s%s%s", Temp1, Temp2, Temp3);
		mEmployee[idEmployee] = employee;
	}

	public static void settings(int maxPeople, String[] mEmployee, int totalEmployees, String password) {
		int menu;
		do {
			System.out.println("\n[1] - View All Employees");
			System.out.println("[2] - Reset Admin Panel");
			System.out.println("[3] - Change password");
			System.out.println("[0]- Quit");

			System.out.print("Select option: ");
			menu = input.nextInt();

			if(menu == 1) {
				System.out.print("Your password: ");
				String passwd = input.nextLine();
				passwd = input.nextLine();
				if(password.equals(passwd)) {
					viewAllPeople(maxPeople, mEmployee, totalEmployees);
				} else {
					System.out.println("Incorrect password");
				}
			}
			if(menu == 2) {
				System.out.println("In creation");
			}
			if(menu == 3) {
				System.out.println("In creation");
			}
		} while (menu != 0);

	}
	public static void viewAllPeople(int maxPeople, String[] mPeople, int totalEmployees) {
		System.out.printf("\nTotal Employees: %d\n", totalEmployees);
		for (int i = 0; i < maxPeople; i++) {
			if (!mPeople[i].equals("")) {
				System.out.println(mPeople[i]);
			}
		}
	}
	public static void access(int maxPeople, String[] mEmployee, int[] idsEmployee, int[] idsSyndicate, String[][] undoEmployee, int[][] undoIdsEmployee, int[][] undoIdsSyndicate, int[] undoVerify, int[] redoVerify) {
		newUndo(maxPeople, mEmployee, idsEmployee, idsSyndicate, undoEmployee, undoIdsEmployee, undoIdsSyndicate, undoVerify);
		for(int i = 0; i < maxPeople; i++) {
			redoVerify[i] = 0;
			return;
		}
	}
	public static void newUndo(int maxPeople, String[] mEmployee, int[] idsEmployee, int[] idsSyndicate, String[][] undoEmployee, int[][] undoIdsEmployee, int[][] undoIdsSyndicate, int[] undoVerify) {
		for(int i = 0; i < maxPeople; i++) {
			if(undoVerify[i] == 0) {
				for(int j = 0; j < maxPeople; j++) {
					undoEmployee[i][j] = mEmployee[j];
					undoIdsEmployee[i][j] = idsEmployee[j];
					undoIdsSyndicate[i][j] = idsSyndicate[j];
				}
				undoVerify[i] = 1;
				return;
			}
		}
	}
	public static void newRedo(int maxPeople, String[] mEmployee, int[] idsEmployee, int[] idsSyndicate, String[][] redoEmployee, int[][] redoIdsEmployee, int[][] redoIdsSyndicate, int[] redoVerify) {
		for(int i = 0; i < maxPeople; i++) {
			if(redoVerify[i] == 0) {
				for(int j = 0; j < maxPeople; j++) {
					redoEmployee[i][j] = mEmployee[j];
					redoIdsEmployee[i][j] = idsEmployee[j];
					redoIdsSyndicate[i][j] = idsSyndicate[j];
				}
				redoVerify[i] = 1;
				return;
			}
		}
	}
	public static void undo(int maxPeople, String[] mEmployee, int[] idsEmployee, int[] idsSyndicate, String[][] undoEmployee, int[][] undoIdsEmployee, int[][] undoIdsSyndicate, String[][] redoEmployee, int[][] redoIdsEmployee, int[][] redoIdsSyndicate, int[] undoVerify, int[] redoVerify) {
		for(int i = maxPeople - 1; i >= 0; i--) {
			if(undoVerify[i] == 1) {
				newRedo(maxPeople, mEmployee, idsEmployee, idsSyndicate, redoEmployee, redoIdsEmployee, redoIdsSyndicate, redoVerify);
				for(int j = 0; j < maxPeople; j++) {
					mEmployee[j] = undoEmployee[i][j];
					idsEmployee[j] = undoIdsEmployee[i][j];
					idsSyndicate[j] = undoIdsSyndicate[i][j];
				}
				undoVerify[i] = 0;
				System.out.println("Undone");
				return;
			}
		}
		System.out.println("Undo unavailable");
	}
	public static void redo(int maxPeople, String[] mEmployee, int[] idsEmployee, int[] idsSyndicate, String[][] undoEmployee, int[][] undoIdsEmployee, int[][] undoIdsSyndicate, String[][] redoEmployee, int[][] redoIdsEmployee, int[][] redoIdsSyndicate, int[] undoVerify, int[] redoVerify) {
		for(int i = maxPeople - 1; i >=0 ; i--) {
			if(redoVerify[i] == 1) {
				newUndo(maxPeople, mEmployee, idsEmployee, idsSyndicate, undoEmployee, undoIdsEmployee, undoIdsSyndicate, undoVerify);
				for(int j = 0; j < maxPeople; j++) {
					mEmployee[j] = redoEmployee[i][j];
					idsEmployee[j] = redoIdsEmployee[i][j];
					idsSyndicate[j] = redoIdsSyndicate[i][j];
				}
				redoVerify[i] = 0;
				System.out.println("Done");
				return;
			}
		}
		System.out.println("Redo unavailable");
	}

	public static void main(String[] args) {
		System.out.println("*********************************************");
		System.out.println("*******         Administrator         *******");
		System.out.println("*********************************************");

		System.out.print("Total Employees: ");
		int people = input.nextInt();
		int maxPeople = 10 * people;

		System.out.print("Day: ");
		String day = input.nextLine();
		day = input.nextLine();

		System.out.print("Month: ");
		String month = input.nextLine();

		System.out.print("Year: ");
		String year = input.nextLine();

		System.out.print("Password: ");
		String password = input.nextLine();

		int totalEmployees, menu;
		int[][] calendar = new int[12][31];
		int[] mensalmenteIds = new int[maxPeople];
		int[] bisemanalmenteIds = new int[maxPeople];
		int[] semanalmenteIds = new int[maxPeople];

		String[] mEmployee = new String[maxPeople];
		String[][] undoEmployee = new String[maxPeople][maxPeople];
		String[][] redoEmployee = new String[maxPeople][maxPeople];
		int[] idsEmployee = new int[maxPeople];
		int[] idsSyndicate = new int[maxPeople];
		int[][] undoIdsEmployee = new int[maxPeople][maxPeople];
		int[][] redoIdsEmployee = new int[maxPeople][maxPeople];
		int[][] undoIdsSyndicate = new int[maxPeople][maxPeople];
		int[][] redoIdsSyndicate = new int[maxPeople][maxPeople];
		int[] undoVerify = new int[maxPeople];
		int[] redoVerify = new int[maxPeople];
		initSystem(maxPeople, mEmployee);

		do {
			System.out.println("");
			totalEmployees = newTotalEmployees(maxPeople, mEmployee);
			if(totalEmployees < people) {
				System.out.println("[1] - New Employee");
			}
			System.out.println("[2] - Remove Employee");
			System.out.println("[3] - Edit Employee");
			System.out.println("[4] - Insert Point Card");
			System.out.println("[5] - Insert Sale Results");
			System.out.println("[6] - Insert Service Fee");
			System.out.println("[7] - Payroll");
			System.out.println("[8] - Undo");
			System.out.println("[9] - Redo");
			System.out.println("[10] - Payment Schedule");
			System.out.println("[11] - New Payment Schedule");

			System.out.println("\nMore:");
			System.out.println("[12] - Settings");
			System.out.println("[0]- Quit");

			System.out.print("Select option: ");
			menu = input.nextInt();

			if(menu == 1) {
				if(totalEmployees < people) {
					newEmployee(maxPeople, mEmployee, idsEmployee, idsSyndicate, undoEmployee, undoIdsEmployee, undoIdsSyndicate, undoVerify, redoVerify);
				} else {
					System.out.println("Invalid Option");
				}
			}
			if(menu == 2) {
				removeEmployee(maxPeople, mEmployee, idsEmployee, idsSyndicate, undoEmployee, undoIdsEmployee, undoIdsSyndicate, undoVerify, redoVerify);
			}
			if(menu == 3) {
				editEmployee(maxPeople, mEmployee, idsEmployee, idsSyndicate, undoEmployee, undoIdsEmployee, undoIdsSyndicate, undoVerify, redoVerify);
			}
			if(menu == 6) {
				insertFee(maxPeople, mEmployee, idsEmployee, idsSyndicate, undoEmployee, undoIdsEmployee, undoIdsSyndicate, undoVerify, redoVerify);
			}
			if(menu == 8) {
				undo(maxPeople, mEmployee, idsEmployee, idsSyndicate, undoEmployee, undoIdsEmployee, undoIdsSyndicate, redoEmployee, redoIdsEmployee, redoIdsSyndicate, undoVerify, redoVerify);
			}
			if(menu == 9) {
				redo(maxPeople, mEmployee, idsEmployee, idsSyndicate, undoEmployee, undoIdsEmployee, undoIdsSyndicate, redoEmployee, redoIdsEmployee, redoIdsSyndicate, undoVerify, redoVerify);
			}
			if(menu == 10) {
			}
			if(menu == 12) {
				settings(maxPeople, mEmployee, totalEmployees, password);
			}
			if(menu == 0) System.out.println("Thanks");

		} while (menu != 0);

	}
}