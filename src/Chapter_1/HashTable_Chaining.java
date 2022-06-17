package Chapter_1;

import Chapter_1.HashTable.Slot;

/*
 �ؽ����̺��� ü�̴� ����� ����� ����
 
 ü�̴� ����̶� :
 ���� �ؽ� �Ǵ� Open Hashing ��� �� �ϳ�: �ؽ� ���̺� ������� ���� ������ Ȱ���ϴ� ���
�浹�� �Ͼ��, ��ũ�� ����Ʈ��� �ڷ� ������ ����ؼ�, ��ũ�� ����Ʈ�� �����͸� �߰��� �ڿ� ������Ѽ� �����ϴ� ���
 */
public class HashTable_Chaining {
	public Slot[] hashTable;

	public HashTable_Chaining(Integer size) {
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
			Slot findSlot = this.hashTable[address];  //�Ǿտ� �ִ� ���� ������ ��
			Slot prevSlot = this.hashTable[address];

			while (findSlot != null) {
				if (findSlot.key == key) {
					findSlot.value = value;
					return true;
				} else { //��ȸ
					prevSlot = findSlot; //���� ���� 
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
					findSlot = findSlot.next; //��忡�� ��ȸ 
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
