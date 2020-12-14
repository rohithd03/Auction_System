package application;

import java.sql.SQLException;
import java.util.ArrayList;

public class Item extends AbsDBMethods{
	
	protected String photo, name;
	protected int item_id, starting_price, status, bidprice;
	protected int bidder_id, seller_id;
	public ArrayList<Item> itemlist = new ArrayList<Item>();
	public ArrayList<Item> accepteditemlist = new ArrayList<Item>();
	public ArrayList<Item> rejecteditemlist = new ArrayList<Item>();
	public ArrayList<Item> pendingitemlist = new ArrayList<Item>();
	public ArrayList<Item> selleritemlist = new ArrayList<Item>();
	public ArrayList<Item> bidderitemlist = new ArrayList<Item>();
	public ArrayList<Object[]> list;
	private String where = "";
	private String values = "";
	public static String label = "";
	
	public Item() {
		getinfo();
	}
	
	public void SubmitItem(String pic, String name, int id, int startprice, int stat) {
		this.photo = pic;
		this.name = name;
		this.seller_id = id;
		this.starting_price = startprice;
		this.status = stat;
		this.bidprice = 0;
		this.bidder_id = 0;
		this.values = "null, \"" + this.name + "\", \"" + this.photo + "\", " + this.starting_price + " , " + this.status + " , " + this.bidder_id + " , " + this.seller_id + " , " + this.starting_price;
		
		try {
			this.insertToDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getStarting_price() {
		return starting_price;
	}

	public void setStarting_price(int starting_price) {
		this.starting_price = starting_price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getBidprice() {
		return bidprice;
	}

	public void setBidprice(int bidprice) {
		this.bidprice = bidprice;
	}

	public int getBidder_id() {
		return bidder_id;
	}

	public void setBidder_id(int bidder_id) {
		this.bidder_id = bidder_id;
	}

	public int getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}

	public Item(String pic, String name, int id, int startprice, int status, int bidderid, int sellerid, int bidprice) {
		this.photo = pic;
		this.name = name;
		this.item_id = id;
		this.starting_price = startprice;
		this.status = status;
		this.bidder_id = bidderid;
		this.seller_id = sellerid;
		this.bidprice = bidprice;
	}
	
	public void SubmitItem(String pic, String name, int id, int startprice, int status, int bidderid, int sellerid, int bidprice) {
		this.setPhoto(pic);
		this.setName(name);
		this.setItem_id(id);
		this.setStarting_price(startprice);
		this.setStatus(status);
		this.setBidder_id(bidderid);
		this.setSeller_id(sellerid);
		this.setBidprice(bidprice);
		this.values = "null, \"" + name + "\", \"" + photo + "\", " + starting_price + " , " + status + " , " + bidder_id + " , " + seller_id + " , " + starting_price;
		
		try {
			this.insertToDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getinfo() {
		list = getall();
		for(Object[] item :list) {
			itemlist.add(new Item((String)item[2], (String)item[1], (int)item[0], (int)item[3], (int)item[4], (int)item[5], (int)item[6], (int)item[7]));
		}
	}
	

	@Override
	protected String getAttributes() {
		return "id,name,photo,startprice,status,starttime,endtime,bidderid,sellerid,bidprice";
	}

	@Override
	protected String getValues() {
		return this.values;
	}
	
	@Override
	protected String getWhere() {
		return this.getwhere();
	}
	
	public ArrayList<Item> getItemlist(){return this.itemlist; }
	public String getwhere() {return this.where; }
	public void setwhere(String where) {this.where = where; }
	
	public ArrayList<Item> getAcceptedItemlist() {
		for(Item i : itemlist) {
			if(i.getStatus() == 1) {
				accepteditemlist.add(i);
			}
		}
		
		return accepteditemlist;
	}
	public ArrayList<Item> getRejectedItemlist() {
		for(Item i : itemlist) {
			if(i.getStatus() == -1) {
				accepteditemlist.add(i);
			}
		}
		
		return accepteditemlist;
	}
	public ArrayList<Item> getPendingItemlist() {
		for(Item i : itemlist) {
			if(i.getStatus() == 0) {
				accepteditemlist.add(i);
			}
		}
		
		return accepteditemlist;
	}
	
	public ArrayList<Item> getsellerItemlist(int id) {
		for(Item i : itemlist) {
			if(i.getSeller_id() == id) {
				selleritemlist.add(i);
			}
		}
		
		
		return selleritemlist;
	}
	
	public ArrayList<Item> getbidderItemlist(int id) {
		for(Item i : itemlist) {
			if(i.getBidder_id() == id) {
				bidderitemlist.add(i);
			}
		}
		
		
		return bidderitemlist;
	}
	
	public void SubmitBidPrice(int id, int price, int bid_id) throws SQLException {
		getinfo();
		for(Item i : itemlist) {
			if(i.getItem_id() == id) {
				if(i.getBidprice() < price) {
					this.where = "id = " + id;
					this.values = "bidprice = " + price + ", bidderid = " + bid_id;
					this.updateToDB();
					label = "";
				}else {
					label = "Enter Valid Bid Amount!";
					System.out.println("Enter Valid Bid Amount");
				}
			}
		}
		//this.setwhere("bidprice = " + price);
	}
	
	public void ChangeStatus(int stat, int id) throws SQLException {
		getinfo();
		for(Item i : itemlist) {
			if(i.getItem_id() == id) {
				this.where = "id = " + id;
				if(stat == 1) {
					this.values = "status = " + stat;
					this.updateToDB();
					System.out.println("Updated Successfully!");
				}else {
					this.delete("");
					System.out.println("Deleted Successfully!");
				}
			}
		}
	}
	

}
