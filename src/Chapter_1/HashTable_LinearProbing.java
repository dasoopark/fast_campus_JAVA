package Chapter_1;

public class HashTable_LinearProbing {
	public Slot[] hashTable;

	public HashTable_LinearProbing(Integer size) {
		this.hashTable = new Slot[size];
	}

	public class Slot {
		String key;
		String value;
		Slot next;

		Slot(String key, String value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
	}

	public int hashFunc(String key) {
		return (int) (key.charAt(0)) % this.hashTable.length;
	}

	public boolean SaveData(String key, String value) {
		Integer address = this.hashFunc(key);

		if (this.hashTable[address] != null) {
			Slot findSlot = this.hashTable[address];  //맨앞에 있는 슬롯 가지게 됨
			Slot prevSlot = this.hashTable[address];

			while (findSlot != null) {
				if (findSlot.key == key) {
					findSlot.value = value;
					return true;
				} else { //순회
					prevSlot = findSlot; //정보 저장 
					findSlot = findSlot.next;
				}
			}
			prevSlot.next = new Slot(key, value);
			
		} else {
			this.hashTable[address] = new Slot(key, value);
		}
		return true;
	}

	public String getData(String key) {
		Integer address = this.hashFunc(key);
		
		if(this.hashTable[address]!=null) {
			Slot findSlot = this.hashTable[address];
			while(findSlot != null) {
				if(findSlot.key == key) {
					return findSlot.value;
				}else {
					findSlot = findSlot.next; //헤드에서 순회 
				}
			}
			return null;
		}else {
			return null;
		}
	}
	public static void main(String[] args) {
		HashTable_Chaining mainObject= new HashTable_Chaining(20);
		mainObject.SaveData("DaveLee", "01022223333");
		mainObject.SaveData("fun-coding", "01033334444");
		mainObject.SaveData("David", "01044445555");
		mainObject.SaveData("Dave", "01055556666");
		System.out.println(mainObject.getData("DaveLee"));
	
	}

}