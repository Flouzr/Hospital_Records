public class PatientList {
	
	private static PatientNode head;
    private int size;
    private static PatientIndexTree tree = new PatientIndexTree();
    
    public PatientList() {
		
	}
    
    public int size() {
        return size;
    }
    
    private class PatientNode {
        private PatientVisitNode patientVisitNode;
        private String patientName;
        private String patientInfo;
        private PatientNode next;
 
        /**
         * Creates a new node with a null next field
         * @param pName Name of the patient
         * @param pInfo Info/condition of patient
         */
        private PatientNode(String pName, String pInfo) {
            patientName = pName;
            patientInfo = pInfo;
            patientVisitNode = null;
            next = null;
        }
 
        /**
         * Creates a new node that references another node
         * @param pName Name of the patient
         * @param pInfo Info/condition of patient
         * @param nextRef Node to reference
         */
        private PatientNode(String pName, String pInfo, PatientNode nextRef) {
            patientName = pName;
            patientInfo = pInfo;
            patientVisitNode = null;
            next = nextRef;
        }

		public PatientNode() {
			patientName = "Empty";
            patientInfo = "Empty";
            patientVisitNode = null;
            next = null;
		}

		public String getPatientName() {
			return patientName;
		}

		public String getPatientInfo() {
			return patientInfo;
		}


		public void setPatientName(String pName) {
			patientName = pName;
		}

		public void setPatientInfo(String pInfo) {
			patientInfo = pInfo;
		}

		public void addVisit(String pName, String phyName, String medCond) {
            patientVisitNode.addVisit(pName, phyName, medCond);
		}

        public class PatientVisitNode {
            private VisitList visitNode;
            private PatientVisitNode next;

            public PatientVisitNode(){
                visitNode = new VisitList();
                next = null;
            }

            public PatientVisitNode(VisitList.VisitNode nextRef, PatientVisitNode pVNode){
                visitNode = pVNode.visitNode;
                next = pVNode;
            }

            public void addVisit(String pName, String phyName, String medCond){
                visitNode.add(pName, phyName, medCond);
                next = null;
            }

            public void addVisit(String pName, String phyName, String medCond, VisitList.VisitNode next){
                visitNode.add(pName, phyName, medCond);
                next = visitNode.getNext(0);
            }
        }
    }    
    
    public void addPatientVisit(String ptName, String phName, String ptCond) {
    	int index = getIndex(ptName);
    	PatientNode patientNode = getNode(index);
    	patientNode.patientVisitNode.addVisit(ptName, phName, ptCond);
    }
    
    public PatientNode getNode(int index){
        PatientNode pNode = head;
        for (int i = 0; i < index && pNode != null; i++){
            pNode = pNode.next;
        }
        return pNode;
    }
    
    public String getPatientName (int patientIndex){
      if (patientIndex < 0 || patientIndex > size) {
          throw new IndexOutOfBoundsException("" + patientIndex);
      }
      PatientNode pNode = getNode(patientIndex);
      return pNode.getPatientName();
  }
    
    public String getPatientInfo(int patientIndex) {
    	if (patientIndex < 0 || patientIndex > size) {
            throw new IndexOutOfBoundsException("" + patientIndex);
        }
    	PatientNode pNode = getNode(patientIndex);
        return pNode.getPatientInfo();
	}
    
    private void addAfter( String patientName, String patientInfo,PatientNode patientNode){
        patientNode.next = new PatientNode(patientName, patientInfo, patientNode);
        tree.insert(patientName, patientNode.next);
    }
    
    private void addFirst(String patientName, String patientInfo){
        head = new PatientNode(patientName, patientInfo);
        tree.insert(patientName, head);
    }
    
    public void addPatient(int index, String patientName, String patientInfo){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("" + index);
        }
        if (index == 0){
            addFirst(patientName, patientInfo);
        } else {
            PatientNode pNode = getNode(index - 1);
            addAfter(patientName, patientInfo, pNode);
        }
        size++;
    }
    
    public int getIndex(String patientName){
        int index = 0;
        PatientNode pNode = head;
        while(pNode != null && index < size){
            if (pNode.patientName.equals(patientName)){
                return index;
            }
            index++;
            pNode = pNode.next;
        }
        return 0;
    }
    
    
//    public String getPatientVisits(String name) {
//    	String temp = "";
//    	PatientNode pat = tree.find(name);
//        for (int i = 0; i < pat.patientVisitNode.visitNode.size(); i++) {
//            temp += pat.patientVisitNode.visitNode.getPatientName(i) + "|" + pat.patientVisitNode.visitNode.getPhysicianName(i) + "|" + pat.patientVisitNode.visitNode.getMedicalCondition(i) + "\n";
//        }
//        return temp;
//    }
    
    public String printTree() {
    	return tree.treeTraversal();
    }   
    
    public static class PatientIndexTree {
    	 
        private static PatientTreeNode root;
    	String patientList = "";
         
        public PatientIndexTree() {
             
        }
       
        public PatientNode find(String name){
        	PatientTreeNode pos = root;
        	PatientTreeNode temp;
            
        	if (root == null) {
                return pos.ptNode;
            }
        	
            while (true) {
                // go down left branch
                if (name.compareTo(pos.patientName) < 0) {
                    if (pos.left != null) {
                        pos = pos.left;
                    } else {
                    	temp = pos;
                        break;
                    }
                // go down right branch
                } else if (name.compareTo(pos.patientName) > 0) {
                    if (pos.right != null) {
                        pos = pos.right;
                    } else {
                    	temp = pos;
                        break;
                    }
                // name found, insertion not needed
                } else {
                	temp = pos;
                
                    break;
                }
            }
            return temp.ptNode;
        }
         
        public void insert(String name, PatientNode node) {
            // empty tree, insert new patient
            if (root == null) { 
                root = new PatientTreeNode(name, node);
                return;
            }
             
            PatientTreeNode pos = root;
             
            while (true) {
                // go down left branch
                if (name.compareTo(pos.patientName) < 0) {
                    if (pos.left != null) {
                        pos = pos.left;
                    } else {
                        pos.left = new PatientTreeNode(name, node);
                        break;
                    }
                // go down right branch
                } else if (name.compareTo(pos.patientName) > 0) {
                    if (pos.right != null) {
                        pos = pos.right;
                    } else {
                        pos.right = new PatientTreeNode(name, node);
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
    		patientList = "";
    		inOrderTraversal(root);
    		return patientList;
    	}
    	
    	// recursively traverse tree
    	private void inOrderTraversal(PatientTreeNode tree) {
    		if (tree != null) {
    			inOrderTraversal(tree.left);
    			patientList += tree.patientName + " | " + tree.ptNode.getPatientInfo() + "\n";
    			inOrderTraversal(tree.right);
    		}
    	}
    	
        private class PatientTreeNode {
            private String patientName;
            private PatientNode ptNode;
            private PatientTreeNode left;
            private PatientTreeNode right;
             
            private PatientTreeNode(String name, PatientNode node) {
                patientName = name;
                ptNode = node;
                left = null;
                right = null;
            }
        }
        
    }
}
