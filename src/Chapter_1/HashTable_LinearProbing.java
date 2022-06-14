package Chapter_1;

/*
  LinearProbing 기법을 이용한 해쉬테이블
  	• 폐쇄 해슁 또는 Close Hashing 기법 중 하나: 
  	해쉬 테이블 저장공간 안에서 충돌 문제를 해결하는 기법
	• 충돌이 일어나면, 해당 hash address의 다음 address부터 맨 처음 나오는
	  빈공간에 저장하는 기법  ○ 저장공간 활용도를 높이기 위한 기법
 */
public class HashTable_LinearProbing {
	public Slot[] hashTable;

	public HashTable_LinearProbing(Integer size) {
		this.hashTable = new Slot[size];
	}

	public class Slot {
		String key;
		String value;

		Slot(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}

	public int hashFunc(String key) {
		return (int) (key.charAt(0)) % this.hashTable.length;
	}

	 public boolean saveData(String key, String value) {
	        Integer address = this.hashFunc(key);
	        if (this.hashTable[address] != null) {
	            if (this.hashTable[address].key == key) {
	                this.hashTable[address].value = value;
	                return true;
	            } else {
	                Integer currAddress = address + 1; //다음 슬롯 주소로 넘어가게 됨. (Linear 기법) -> onenote 그림 참고
	                while (this.hashTable[currAddress] != null) {
	                    if (this.hashTable[currAddress].key == key) {
	                        this.hashTable[currAddress].value = value;
	                        return true;
	                    } else {
	                        currAddress++; //또 비어있지 않다면 다음 슬롯 주소로 넘어가게 됨.
	                        if (currAddress >= this.hashTable.length) { //끝자락일때
	                            return false;
	                        }                        
	                    }
	                }
	                this.hashTable[currAddress] = new Slot(key, value);
	                return true;
	            }
	        } else {
	            this.hashTable[address] = new Slot(key, value);
	        }
	        return true;
	    }
	    
	    public String getData(String key) {
	        Integer address = this.hashFunc(key);
	        if (this.hashTable[address] != null) {
	            if (this.hashTable[address].key == key) {
	                return this.hashTable[address].value;
	            } else {
	                // 참고: 다음 코드를 수정합니다.
	                // Integer currAddress = address + 1;                 
	                // 예외 케이스로, 키에 해당하는 주소가 가장 마지막 슬롯일 경우, 
	                // this.hashTable[address + 1] 에 해당하는 배열은 없기 때문에, 
	                // 예외 케이스에서도 동작하도록 currAddress 는 address 만 대입하고 진행합니다
	                Integer currAddress = address; // 수정 
	                while (this.hashTable[currAddress] != null) {
	                    if (this.hashTable[currAddress].key == key) {
	                        return this.hashTable[currAddress].value;
	                    } else {
	                        currAddress++;
	                        if (currAddress >= this.hashTable.length) { //마지막 슬롯
	                            return null;
	                        }
	                    }
	                }
	                return null;
	            }
	        } else {
	            return null;
	        }
	    }
	public static void main(String[] args) {
		HashTable_LinearProbing mainObject= new HashTable_LinearProbing(20);
		mainObject.saveData("DaveLee", "01022223333");
		mainObject.saveData("fun-coding", "01033334444");
		mainObject.saveData("David", "01044445555");
		mainObject.saveData("Dave", "01055556666");
		System.out.println(mainObject.getData("DaveLee"));
	
	}

}