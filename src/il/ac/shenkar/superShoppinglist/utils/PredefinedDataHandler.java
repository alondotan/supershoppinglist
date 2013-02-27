package il.ac.shenkar.superShoppinglist.utils;

import il.ac.shenkar.superShoppinglist.R;
import il.ac.shenkar.superShoppinglist.beans.Category;
import il.ac.shenkar.superShoppinglist.beans.Product;


import java.util.ArrayList;

public class PredefinedDataHandler {
	

	private ArrayList<Category> predifiedCategoryList;

	public PredefinedDataHandler(){


		predifiedCategoryList = new ArrayList<Category>();
		predifiedCategoryList.add(new Category("����",R.drawable.drinks));
		predifiedCategoryList.add(new Category("������",R.drawable.candy));
		predifiedCategoryList.add(new Category("�����",R.drawable.beauty));
		predifiedCategoryList.add(new Category("�����",R.drawable.cleaning));
		predifiedCategoryList.add(new Category("�������",R.drawable.baby));
		predifiedCategoryList.add(new Category("����� ������",R.drawable.frouts));
		predifiedCategoryList.add(new Category("������",R.drawable.bread));
		predifiedCategoryList.add(new Category("����� ���",R.drawable.milk));
		predifiedCategoryList.add(new Category("������",R.drawable.frozen));
		predifiedCategoryList.add(new Category("����� ����",R.drawable.basic));
		predifiedCategoryList.add(new Category("���",R.drawable.other));
				
	}
	
	public ArrayList<Product> getPredefinedProductsList(){
		ArrayList<Product> predifiedProductsList;

		predifiedProductsList = new ArrayList<Product>();
		predifiedProductsList.add(new Product("���� �����",0,"","�����"));		
		predifiedProductsList.add(new Product("���� �����",0,"","�����"));		
		predifiedProductsList.add(new Product("���� �����",0,"","�����"));		
		predifiedProductsList.add(new Product("���� �����",0,"","�����"));		
		predifiedProductsList.add(new Product("���� ������",0,"","�����"));		
		predifiedProductsList.add(new Product("��������",0,"","�����"));
		predifiedProductsList.add(new Product("���� ���",0,"","�����"));
		predifiedProductsList.add(new Product("����",0,"","�����"));
		predifiedProductsList.add(new Product("����",0,"","�����"));
		predifiedProductsList.add(new Product("���� ����",0,"","�����"));
		predifiedProductsList.add(new Product("����",0,"","����"));
		predifiedProductsList.add(new Product("����",0,"","����"));
		predifiedProductsList.add(new Product("���",0,"","����"));
		predifiedProductsList.add(new Product("�����",0,"","����"));
		predifiedProductsList.add(new Product("���",0,"","����"));
		predifiedProductsList.add(new Product("�����",0,"","������"));
		predifiedProductsList.add(new Product("����",0,"","������"));
		predifiedProductsList.add(new Product("�����",0,"","������"));
		predifiedProductsList.add(new Product("�������",0,"","������"));
		predifiedProductsList.add(new Product("������",0,"","������"));
		predifiedProductsList.add(new Product("�������",0,"","������"));
		predifiedProductsList.add(new Product("������",0,"","������"));
		predifiedProductsList.add(new Product("������",0,"","������"));
		predifiedProductsList.add(new Product("�������",0,"","�������"));
		predifiedProductsList.add(new Product("������",0,"","�������"));
		predifiedProductsList.add(new Product("����",0,"","�������"));
		predifiedProductsList.add(new Product("���",0,"","����� ������"));
		predifiedProductsList.add(new Product("��� ����",0,"","����� ������"));
		predifiedProductsList.add(new Product("�������",0,"","����� ������"));
		predifiedProductsList.add(new Product("��������",0,"","����� ������"));
		predifiedProductsList.add(new Product("���� ����",0,"","����� ������"));
		predifiedProductsList.add(new Product("���� ����",0,"","����� ������"));
		predifiedProductsList.add(new Product("���� ����",0,"","����� ������"));
		predifiedProductsList.add(new Product("���� ����",0,"","����� ������"));
		predifiedProductsList.add(new Product("���� ����",0,"","����� ������"));
		predifiedProductsList.add(new Product("���� ���",0,"","����� ������"));
		predifiedProductsList.add(new Product("���� ����",0,"","����� ������"));
		predifiedProductsList.add(new Product("������",0,"","����� ������"));
		predifiedProductsList.add(new Product("�������",0,"","����� ������"));
		predifiedProductsList.add(new Product("���",0,"","����� ������"));
		predifiedProductsList.add(new Product("���",0,"","����� ������"));
		predifiedProductsList.add(new Product("������",0,"","����� ������"));
		predifiedProductsList.add(new Product("�����",0,"","����� ������"));
		predifiedProductsList.add(new Product("������",0,"","����� ������"));
		predifiedProductsList.add(new Product("��������",0,"","����� ������"));
		predifiedProductsList.add(new Product("�������",0,"","����� ������"));
		predifiedProductsList.add(new Product("�����",0,"","����� ������"));
		predifiedProductsList.add(new Product("�������",0,"","����� ������"));
		predifiedProductsList.add(new Product("������� �����",0,"","����� ������"));
		predifiedProductsList.add(new Product("�����",0,"","����� ������"));
		predifiedProductsList.add(new Product("������",0,"","����� ������"));
		predifiedProductsList.add(new Product("����",0,"","����� ������"));
		predifiedProductsList.add(new Product("���� ����",0,"","����� ������"));
		predifiedProductsList.add(new Product("���",0,"","����� ������"));
		predifiedProductsList.add(new Product("�������",0,"","����� ������"));
		predifiedProductsList.add(new Product("������",0,"","����� ������"));
		predifiedProductsList.add(new Product("���",0,"","����� ������"));
		predifiedProductsList.add(new Product("����",0,"","����� ������"));
		predifiedProductsList.add(new Product("������",0,"","����� ������"));
		predifiedProductsList.add(new Product("����",0,"","����� ������"));		
		predifiedProductsList.add(new Product("��� ���",0,"","������"));
		predifiedProductsList.add(new Product("��� ��",0,"","������"));
		predifiedProductsList.add(new Product("������",0,"","������"));
		predifiedProductsList.add(new Product("�����",0,"","������"));
		predifiedProductsList.add(new Product("���",0,"","������"));
		predifiedProductsList.add(new Product("�������",0,"","������"));
		predifiedProductsList.add(new Product("�����",0,"","������"));
		predifiedProductsList.add(new Product("������",0,"","������"));
		predifiedProductsList.add(new Product("���",0,"","����� ���"));
		predifiedProductsList.add(new Product("����� ����",0,"","����� ���"));
		predifiedProductsList.add(new Product("����",0,"","����� ���"));
		predifiedProductsList.add(new Product("����� �����",0,"","����� ���"));
		predifiedProductsList.add(new Product("��� ����",0,"","����� ���"));
		predifiedProductsList.add(new Product("���� ���",0,"","����� ���"));
		predifiedProductsList.add(new Product("���� ����",0,"","����� ���"));
		predifiedProductsList.add(new Product("������",0,"","����� ���"));
		predifiedProductsList.add(new Product("�������",0,"","����� ���"));
		predifiedProductsList.add(new Product("�����",0,"","����� ���"));
		predifiedProductsList.add(new Product("����",0,"","����� ���"));
		predifiedProductsList.add(new Product("����� ����",0,"","����� ���"));
		predifiedProductsList.add(new Product("���� �����",0,"","����� ���"));
		predifiedProductsList.add(new Product("���� ������",0,"","����� ���"));
		predifiedProductsList.add(new Product("����",0,"","����� ���"));
		predifiedProductsList.add(new Product("�������",0,"","����� ���"));
		predifiedProductsList.add(new Product("����",0,"","����� ���"));
		predifiedProductsList.add(new Product("����� ����� ������",0,"","����� ���"));
		predifiedProductsList.add(new Product("������� ����",0,"","������"));
		predifiedProductsList.add(new Product("����� �����",0,"","������"));
		predifiedProductsList.add(new Product("������ �����",0,"","������"));
		predifiedProductsList.add(new Product("��� ����� ����",0,"","������"));
		predifiedProductsList.add(new Product("��� ����",0,"","������"));
		predifiedProductsList.add(new Product("���� ���� ������",0,"","������"));
		predifiedProductsList.add(new Product("��� �����",0,"","����� ����"));
		predifiedProductsList.add(new Product("����",0,"","����� ����"));
		predifiedProductsList.add(new Product("���",0,"","����� ����"));
		predifiedProductsList.add(new Product("��� ����",0,"","����� ����"));
		predifiedProductsList.add(new Product("����",0,"","����� ����"));
		predifiedProductsList.add(new Product("����",0,"","����� ����"));
		predifiedProductsList.add(new Product("������",0,"","����� ����"));
		
		return predifiedProductsList;
	}
	
	public ArrayList<Category> getPredefinedCategoryList(){
		return predifiedCategoryList;
	}
}
