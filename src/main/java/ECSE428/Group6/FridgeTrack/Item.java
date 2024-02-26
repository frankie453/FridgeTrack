/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 14 "model.ump"
// line 86 "model.ump"
public class Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private String name;
  private Unit unit;

  //Item Associations
  private Recipe recipe;
  private List<Record> records;
  private ItemList itemList;
  private ItemCategory itemCategory;
  private Fridge fridge;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(String aName, Unit aUnit, Recipe aRecipe, ItemList aItemList, ItemCategory aItemCategory, Fridge aFridge)
  {
    name = aName;
    unit = aUnit;
    boolean didAddRecipe = setRecipe(aRecipe);
    if (!didAddRecipe)
    {
      throw new RuntimeException("Unable to create item due to recipe. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    records = new ArrayList<Record>();
    boolean didAddItemList = setItemList(aItemList);
    if (!didAddItemList)
    {
      throw new RuntimeException("Unable to create item due to itemList. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddItemCategory = setItemCategory(aItemCategory);
    if (!didAddItemCategory)
    {
      throw new RuntimeException("Unable to create item due to itemCategory. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddFridge = setFridge(aFridge);
    if (!didAddFridge)
    {
      throw new RuntimeException("Unable to create item due to fridge. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setUnit(Unit aUnit)
  {
    boolean wasSet = false;
    unit = aUnit;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public Unit getUnit()
  {
    return unit;
  }
  /* Code from template association_GetOne */
  public Recipe getRecipe()
  {
    return recipe;
  }
  /* Code from template association_GetMany */
  public Record getRecord(int index)
  {
    Record aRecord = records.get(index);
    return aRecord;
  }

  public List<Record> getRecords()
  {
    List<Record> newRecords = Collections.unmodifiableList(records);
    return newRecords;
  }

  public int numberOfRecords()
  {
    int number = records.size();
    return number;
  }

  public boolean hasRecords()
  {
    boolean has = records.size() > 0;
    return has;
  }

  public int indexOfRecord(Record aRecord)
  {
    int index = records.indexOf(aRecord);
    return index;
  }
  /* Code from template association_GetOne */
  public ItemList getItemList()
  {
    return itemList;
  }
  /* Code from template association_GetOne */
  public ItemCategory getItemCategory()
  {
    return itemCategory;
  }
  /* Code from template association_GetOne */
  public Fridge getFridge()
  {
    return fridge;
  }
  /* Code from template association_SetOneToMany */
  public boolean setRecipe(Recipe aRecipe)
  {
    boolean wasSet = false;
    if (aRecipe == null)
    {
      return wasSet;
    }

    Recipe existingRecipe = recipe;
    recipe = aRecipe;
    if (existingRecipe != null && !existingRecipe.equals(aRecipe))
    {
      existingRecipe.removeItem(this);
    }
    recipe.addItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRecords()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Record addRecord(int aId, Date aEnterDate, int aQuantity, Date aExpiryDate)
  {
    return new Record(aId, aEnterDate, aQuantity, aExpiryDate, this);
  }

  public boolean addRecord(Record aRecord)
  {
    boolean wasAdded = false;
    if (records.contains(aRecord)) { return false; }
    Item existingItem = aRecord.getItem();
    boolean isNewItem = existingItem != null && !this.equals(existingItem);
    if (isNewItem)
    {
      aRecord.setItem(this);
    }
    else
    {
      records.add(aRecord);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRecord(Record aRecord)
  {
    boolean wasRemoved = false;
    //Unable to remove aRecord, as it must always have a item
    if (!this.equals(aRecord.getItem()))
    {
      records.remove(aRecord);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRecordAt(Record aRecord, int index)
  {  
    boolean wasAdded = false;
    if(addRecord(aRecord))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecords()) { index = numberOfRecords() - 1; }
      records.remove(aRecord);
      records.add(index, aRecord);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRecordAt(Record aRecord, int index)
  {
    boolean wasAdded = false;
    if(records.contains(aRecord))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRecords()) { index = numberOfRecords() - 1; }
      records.remove(aRecord);
      records.add(index, aRecord);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRecordAt(aRecord, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setItemList(ItemList aItemList)
  {
    boolean wasSet = false;
    if (aItemList == null)
    {
      return wasSet;
    }

    ItemList existingItemList = itemList;
    itemList = aItemList;
    if (existingItemList != null && !existingItemList.equals(aItemList))
    {
      existingItemList.removeItem(this);
    }
    itemList.addItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setItemCategory(ItemCategory aItemCategory)
  {
    boolean wasSet = false;
    if (aItemCategory == null)
    {
      return wasSet;
    }

    ItemCategory existingItemCategory = itemCategory;
    itemCategory = aItemCategory;
    if (existingItemCategory != null && !existingItemCategory.equals(aItemCategory))
    {
      existingItemCategory.removeItem(this);
    }
    itemCategory.addItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setFridge(Fridge aFridge)
  {
    boolean wasSet = false;
    if (aFridge == null)
    {
      return wasSet;
    }

    Fridge existingFridge = fridge;
    fridge = aFridge;
    if (existingFridge != null && !existingFridge.equals(aFridge))
    {
      existingFridge.removeItem(this);
    }
    fridge.addItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Recipe placeholderRecipe = recipe;
    this.recipe = null;
    if(placeholderRecipe != null)
    {
      placeholderRecipe.removeItem(this);
    }
    for(int i=records.size(); i > 0; i--)
    {
      Record aRecord = records.get(i - 1);
      aRecord.delete();
    }
    ItemList placeholderItemList = itemList;
    this.itemList = null;
    if(placeholderItemList != null)
    {
      placeholderItemList.removeItem(this);
    }
    ItemCategory placeholderItemCategory = itemCategory;
    this.itemCategory = null;
    if(placeholderItemCategory != null)
    {
      placeholderItemCategory.removeItem(this);
    }
    Fridge placeholderFridge = fridge;
    this.fridge = null;
    if(placeholderFridge != null)
    {
      placeholderFridge.removeItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "unit" + "=" + (getUnit() != null ? !getUnit().equals(this)  ? getUnit().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "recipe = "+(getRecipe()!=null?Integer.toHexString(System.identityHashCode(getRecipe())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "itemList = "+(getItemList()!=null?Integer.toHexString(System.identityHashCode(getItemList())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "itemCategory = "+(getItemCategory()!=null?Integer.toHexString(System.identityHashCode(getItemCategory())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "fridge = "+(getFridge()!=null?Integer.toHexString(System.identityHashCode(getFridge())):"null");
  }
}