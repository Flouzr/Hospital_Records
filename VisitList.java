public class VisitList {

    private static VisitNode head;
    private int size;

    /**
     * Default constructor to initialize and empty list
     */
    public VisitList() {

    }

    /**
     * Gets the number of patients in the list
     * @return number of patients
     */
    public int size(){
        return size;
    }

    
    /**
     * Get the patient name from the specified index
     * @param index Index of the node
     * @return Patients name of requested node
     */
    public String getPatientName (int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("" + index);
        }
        VisitNode vNode = getNode(index);
        return vNode.patientName;
    }

    public String getOnlyPatientName (int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("" + index);
        }
        VisitNode vNode = getNode(index);
        String text = vNode.patientName;
        String text1 = text.split("\\|")[0].trim();
        return text1;
    }

    public String getOnlyPhysicianName (int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("" + index);
        }
        VisitNode vNode = getNode(index);
        String text = vNode.physicianName;
        String text1 = text.split("\\|")[0].trim();
        System.out.println(text1);
        return text1;
    }

    public String getPhysicianName (int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("" + index);
        }
        VisitNode vNode = getNode(index);
        return vNode.physicianName;
    }

    public String getMedicalCondition (int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("" + index);
        }
        VisitNode vNode = getNode(index);
        return vNode.medicalCondition;
    }

    public VisitNode getNext (int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("" + index);
        }
        VisitNode vNode = getNode(index + 1);
        return vNode.next;
    }

    public String patientsWithCondition (String medicalCondition){
        StringBuilder stringBuilder = new StringBuilder();
    	for (int i = 0; i < size(); i++) {
            if (medicalCondition.equalsIgnoreCase(getMedicalCondition(i))){
                stringBuilder.append(getPatientName(i) + " | " + getPhysicianName(i) + " | " + getMedicalCondition(i) + "\n");
            }
        }
        return stringBuilder.toString();
    }

    public String patientsWithName(String patientName){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            if (patientName.equalsIgnoreCase(getPatientName(i))){
                stringBuilder.append(getPatientName(i) + " | " + getPhysicianName(i) + " | " + getMedicalCondition(i) + "\n");
            }
        }
        return stringBuilder.toString();
    }

    public String patientsWithPhysician (String physician){
    	StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            if (physician.equalsIgnoreCase(getOnlyPhysicianName(i))){
                stringBuilder.append(getPhysicianName(i) + " -> " + getPatientName(i) + " | " + getMedicalCondition(i) + "\n");
            }
        }
    	return stringBuilder.toString();
    }

    public String getVisits (){
        String temp = "";
        for (int i = 0; i < size(); i++) {
            temp += getPatientName(i) + " | " + getPhysicianName(i) + " | " + getMedicalCondition(i) + "\n";
        }
        return temp;
    }

    public String getPatientVisits (String name){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            if(getOnlyPatientName(i).equalsIgnoreCase(name)){
                stringBuilder.append(getPatientName(i) + " -> " + getPhysicianName(i) + " | " + getMedicalCondition(i) + "\n");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Get the patient info from the specified index
     * @param index Index of the node
     * @return Patients info of requested node
     */
    public String getInfo (int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("" + index);
        }
        VisitNode vNode = getNode(index);
        return vNode.medicalCondition;
    }

    /**
     * Replace data in specified node (null will not overwrite
     * current data
     * @param index location of the node
     * @param patientName new name of the patient
     * @param physicianName new info of the patient
     * @return old patient name + info
     */
    public String set(int index, String patientName, String physicianName, String medicalCondition){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("" + index);
        }

        VisitNode vNode = getNode(index);

        String result = vNode.patientName + vNode.physicianName;

        if (patientName == null) {
            vNode.physicianName = physicianName;
        }else if(physicianName == null){
            vNode.patientName = patientName;
        }else if (medicalCondition == null) {
            vNode.medicalCondition = medicalCondition;
        }else {
            vNode.physicianName = physicianName;
            vNode.patientName = patientName;
            vNode.medicalCondition = medicalCondition;
        }

        return result;
    }

    /**
     * Add a new VisitNode at a specified location
     * @param index location to addPatient node
     * @param patientName name of patient
     * @param physicianName patients info
     */
    public void add(int index, String patientName, String physicianName, String medicalCondition){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("" + index);
        }
        if (index == 0){
            addFirst(patientName, physicianName, medicalCondition);
        } else {
            VisitNode vNode = getNode(index - 1);
            addAfter(vNode, patientName, physicianName, medicalCondition);
        }
        size++;
    }

    /**
     * Add a new VisitNode to the end of the PatientList
     * @param patientName name of patient to ass
     * @param physicianName info of patient to addPatient
     * @return true if successful
     */
    public boolean add(String patientName, String physicianName, String medicalCondition){
        add(size, patientName, physicianName, medicalCondition);
        return true;
    }

    private VisitNode getNode(int index){
        VisitNode vNode = head;
        for (int i = 0; i < index && vNode != null; i++){
            vNode = vNode.next;
        }
        return vNode;
    }

    private void addFirst(String patientName, String physicianName, String medicalCondition){
        head = new VisitNode(patientName, physicianName, medicalCondition);
    }

    private void addAfter(VisitNode visitNode, String patientName, String physicianName, String medicalCondition){
        visitNode.next = new VisitNode(patientName, physicianName, medicalCondition, visitNode);   
    }

    private String removeFirst(){
        VisitNode temp = head;
        if (head != null) {
            head = head.next;
        }
        if (temp != null){
            size--;
            String result = temp.patientName + temp.physicianName;
            return result;
        } else {
            return null;
        }
    }
    
    public class VisitNode {
        private String patientName;
        private String physicianName;
        private String medicalCondition;
        private VisitNode next;

        /**
         * Creates a new node with a null next field
         * @param pName Name of the patient
         * @param phyName Info/condition of patient
         */
        private VisitNode(String pName, String phyName, String mCond){
            patientName = pName;
            physicianName = phyName;
            medicalCondition = mCond;
            next = null;
        }

        /**
         * Creates a new node that references another node
         * @param pName Name of the patient
         * @param phyName Info/condition of patient
         * @param nextRef Node to reference
         */
        private VisitNode(String pName, String phyName, String mCond, VisitNode nextRef){
            patientName = pName;
            physicianName = phyName;
            medicalCondition = mCond;
            next = nextRef;
        }
    }
}
