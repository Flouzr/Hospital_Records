public class PhysicianList {

    private static PhysicianNode head;
    private int size;
    private static PhysicianIndexTree tree = new PhysicianIndexTree();
    

    /**
     * Default constructor to initialize and empty list
     */
    public PhysicianList() {

    }

    /**
     * Gets the number of patients in the list
     * @return number of patients
     */
    public int size(){
        return size;
    }

    private static class PhysicianNode {
        private String physicianName;
        private String physicianSpecialty;
        private VisitList visit;
        private PhysicianNode next;

        /**
         * Creates a new node with a null next field
         * @param pName Name of the patient
         * @param pSpecialty Info/condition of patient
         */
        private PhysicianNode(String pName, String pSpecialty){
            physicianName = pName;
            physicianSpecialty = pSpecialty;
            next = null;
            visit = new VisitList();
        }

        /**
         * Creates a new node that references another node
         * @param pName Name of the patient
         * @param pSpecialty Info/condition of patient
         * @param nextRef Node to reference
         */
        private PhysicianNode(String pName, String pSpecialty, PhysicianNode nextRef){
            physicianName = pName;
            physicianSpecialty = pSpecialty;
            next = nextRef;
            visit = new VisitList();
        }
        
        public String getPhysicianName() {
			return physicianName;
		}

		public String getPhysicianSpecialty() {
			return physicianSpecialty;
		}

		public VisitList getVisit() {
			return visit;
		}

		public void setPhysicianName(String pName) {
			physicianName = pName;
		}

		public void setPhysicianSpecialty(String pSpecialty) {
			physicianSpecialty = pSpecialty;
		}

		public void setVisit(VisitList pVisit) {
			visit = pVisit;
		}
    }
    
    public PhysicianNode getNode(int index){
    	PhysicianNode pNode = head;
        for (int i = 0; i < index && pNode != null; i++){
            pNode = pNode.next;
        }
        return pNode;
    }
    
    
    /**
     * Get the patient name from the specified index
     * @param index Index of the node
     * @return Patients name of requested node
     */
    public String getPhysicianName (int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("" + index);
        }
        PhysicianNode pNode = getNode(index);
        return pNode.physicianName;
    }
    /**
     * Get the patient info from the specified index
     * @param index Index of the node
     * @return Patients info of requested node
     */
    public String getPhysicianSpecialty (int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("" + index);
        }
        PhysicianNode pNode = getNode(index);
        return pNode.physicianSpecialty;
    }

//    public String getPhysicianVisits(String name) {
//        String temp = "";
//        PhysicianNode pat = tree.find(name);
//        for (int i = 0; i < pat.patientVisitNode.visitNode.size(); i++) {
//            temp += pat.patientVisitNode.visitNode.getPatientName(i) + "|" + pat.patientVisitNode.visitNode.getPhysicianName(i) + "|" + pat.patientVisitNode.visitNode.getMedicalCondition(i) + "\n";
//        }
//        return temp;
//    }

    private void addAfter(String physicianName, String physicianSpecialty, PhysicianNode physicianNode){
        physicianNode.next = new PhysicianNode(physicianName, physicianSpecialty, physicianNode);
        tree.insert(physicianName, physicianNode.next);
    }

    private void addFirst(String physicianName, String physicianSpecialty){
        head = new PhysicianNode(physicianName, physicianSpecialty);
        tree.insert(physicianName, head);
    }
    
    public void addPhysician(int index, String physicianName, String physicianSpecialty){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("" + index);
        }
        if (index == 0){
            addFirst(physicianName, physicianSpecialty);
        } else {
        	PhysicianNode pNode = getNode(index - 1);
            addAfter(physicianName, physicianSpecialty, pNode);
        }
        size++;
    }

    public int getIndex(String physicianName){
        int index = 0;
        PhysicianNode pNode = head;
        while(pNode != null && index < size){
            if (pNode.physicianName.equals(physicianName)){
                return index;
            }
            index++;
            pNode = pNode.next;
        }
        return 0;
    }
    
    public String printTree() {
    	return tree.treeTraversal();
    }

    /**
     * Node created per patient that holds all
     * of their information
     */
    
    
    public static class PhysicianIndexTree {

    	private static PhysicianTreeNode root;
    	String physicianList = "";
    	
    	public PhysicianIndexTree() {
    		
    	}
    	
    	public boolean find(String name){
        	if (root == null) {
                return false;
            }
             
            PhysicianTreeNode pos = root;
            boolean isThere;
             
            while (true) {
                // go down left branch
                if (name.compareTo(pos.physicianName) < 0) {
                    if (pos.left != null) {
                        pos = pos.left;
                    } else {
                        isThere = false;
                        break;
                    }
                // go down right branch
                } else if (name.compareTo(pos.physicianName) > 0) {
                    if (pos.right != null) {
                        pos = pos.right;
                    } else {
                        isThere = false;
                        break;
                    }
                // name found, insertion not needed
                } else {
                	isThere = true;
                
                    break;
                }
            }
            return isThere;
        }
    	
    	public void insert(String name, PhysicianNode node) {
    		// empty tree, insert new patient
    		if (root == null) { 
    			root = new PhysicianTreeNode(name, node);
    			return;
    		}
    		
    		PhysicianTreeNode pos = root;
    		
    		while (true) {
    			// go down left branch
    			if (name.compareTo(pos.physicianName) < 0) {
    				if (pos.left != null) {
    					pos = pos.left;
    				} else {
    					pos.left = new PhysicianTreeNode(name, node);
    					break;
    				}
    			// go down right branch
    			} else if (name.compareTo(pos.physicianName) > 0) {
    				if (pos.right != null) {
    					pos = pos.right;
    				} else {
    					pos.right = new PhysicianTreeNode(name, node);
    					break;
    				}
   				// name found, insertion not needed
    			} else {
    				break;
    			}
    		}
    	}
    	
    	// access and work with private root
    	public String treeTraversal() {
    		physicianList = "";
    		inOrderTraversal(root);
    		return physicianList;
    	}
    	
    	// recursively traverse tree
    	private void inOrderTraversal(PhysicianTreeNode tree) {
    		if (tree != null) {
    			inOrderTraversal(tree.left);
    			physicianList += tree.physicianName + " | " + tree.ptNode.getPhysicianSpecialty() + "\n";
    			inOrderTraversal(tree.right);
    		}
    	}
    	
    	private class PhysicianTreeNode {
    		private String physicianName;
    		private PhysicianNode ptNode;
    		private PhysicianTreeNode left;
    		private PhysicianTreeNode right;
    		
    		private PhysicianTreeNode(String name, PhysicianNode node) {
    			physicianName = name;
    			ptNode = node;
    			left = null;
    			right = null;
    		}
    	}
    }
}
