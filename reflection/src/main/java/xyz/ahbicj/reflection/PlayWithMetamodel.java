package xyz.ahbicj.reflection;

import xyz.ahbicj.reflection.model.Person;
import xyz.ahbicj.reflection.util.ColumnField;
import xyz.ahbicj.reflection.util.Metamodel;
import xyz.ahbicj.reflection.util.PrimaryKeyField;

import java.util.ArrayList;
import java.util.List;

public class PlayWithMetamodel {
	
	public static void main(String... args) {
		
		Metamodel<Person> metamodel = Metamodel.of(Person.class);
		
		PrimaryKeyField primaryKeyField = metamodel.getPrimaryKey();
		List<ColumnField> columnFields = metamodel.getColumns();
		
		System.out.println("Primary key name = " + primaryKeyField.getName() + 
				", type = " + primaryKeyField.getType().getSimpleName());
		
		for (ColumnField columnField : columnFields) {
			System.out.println("Colum name = " + columnField.getName() + 
					", type = " + columnField.getType().getSimpleName());
		}
	}
}
