package com.product.message.domain;

import java.util.ArrayList;

import com.product.message.enums.OperationIndicator;
import com.product.message.enums.Operations;

/**
 * Class Name: Product
 *
 * Description: A product class contains the product details such as it's name,
 * operation type - ADJUSTMENT/SALE, operation indicator implies what type of
 * adjustment happened ADD, SUBTRACT, MULTIPLY quantity sold or adjusted,price
 * per piece, total price value of a product. Arraylist of adjustment details
 * done on runtime on a product.
 * 
 * @author Sujata Bhatter
 * @version 1.0
 * @since 2017-07-02
 */
public class Product {

	// The name of the product e.g apple or orange, etc.
	private String productName;

	// Kind of operation performed on the product that can be ADJUSTMENT or SALE
	private Operations operation;

	/*
	 * Kind of adjustment operation performed on the product like ADD, SUBTRACT
	 * or MULTIPLY
	 */
	private OperationIndicator operationIndicator;

	// Number of product processed for sale or adjustment
	private int quantity;

	// A single product sale price value
	private double pricePerPC;

	// Total price of a product sold or adjusted
	private double totalProductPrice;

	/*
	 * Stores the message of the runtime adjustment done to product like
	 * Operation Performed: ADD 0.2p to 21 apple. Price adjusted from 2.1p to
	 * 6.3p
	 */
	private ArrayList<String> adjustmentMessage;

	// Parameterised Constructor
	/**
	 * This method is Parameterised Constructor to set the non calculative value
	 * of the product
	 * 
	 * @param productName
	 *            This is the first parameter to the constructor to set product
	 *            name
	 * @param operation
	 *            This is the second parameter to the constructor to set
	 *            operation performed on the product
	 * 
	 * @param operationIndicator
	 *            This is the third parameter to the constructor to set
	 *            operation type
	 * 
	 * @param quantity
	 *            This is the fourth parameter to the constructor to set
	 *            quantity of the product
	 * 
	 * @param pricePerPC
	 *            This is the fifth parameter to the constructor to set price of
	 *            per product
	 */
	public Product(String productName, Operations operation, OperationIndicator operationIndicator, int quantity,
			double pricePerPC) {
		this.productName = productName;
		this.operation = operation;
		this.operationIndicator = operationIndicator;
		this.quantity = quantity;
		this.pricePerPC = pricePerPC;
	}

	// Default Constructor
	public Product() {
	}

	/*
	 * Gets the name of the requested product
	 * 
	 * @return String This returns the name of the product
	 */
	public String getProductName() {
		return productName;
	}

	// Set the name of the product.
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/*
	 * Gets the operation performed on the requested product
	 * 
	 * @return Operations This returns the operation performed on the product
	 */
	public Operations getOperation() {
		return operation;
	}

	// Set the operation type like SALE OR ADJUSTMENT from Operations enum
	public void setOperation(Operations operation) {
		this.operation = operation;
	}

	/*
	 * Gets the operation indicator for the requested product
	 * 
	 * @return OperationIndicator This returns the Operation indicator
	 */
	public OperationIndicator getOperationIndicator() {
		return operationIndicator;
	}

	// Set the product adjustment operation like ADD, SUBSTRACT, or MULTIPLY
	// from OperationIndicator enum
	public void setOperationIndicator(OperationIndicator operationIndicator) {
		this.operationIndicator = operationIndicator;
	}

	/*
	 * Gets the number of the requested product got processed
	 * 
	 * @return int This returns the quantity of the product
	 */
	public int getQuantity() {
		return quantity;
	}

	// Set the quantity of the requested product
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/*
	 * Gets the price per piece of the requested product
	 * 
	 * @return double This returns the price per product
	 */
	public double getPricePerPC() {
		return pricePerPC;
	}

	// Set the price of per requested product
	public void setPricePerPC(double pricePerPC) {
		this.pricePerPC = pricePerPC;
	}

	/*
	 * Gets the price of the requested product post sale or adjustment
	 * 
	 * @return double This returns the totalProductPrice
	 */
	public double getTotalProductPrice() {
		return totalProductPrice;
	}

	// Set the total price of the product processed (i.e. sold or adjusted)
	public void setTotalProductPrice(double totalProductPrice) {
		this.totalProductPrice = totalProductPrice;
	}

	/*
	 * Gets the list of adjustment done to each product during processing
	 * 
	 * @return the list of the adjustment done during runtime
	 */
	public ArrayList<String> getAdjustmentMessage() {
		return adjustmentMessage;
	}

	// Set the adjustment message in the list
	public void setAdjustmentMessage(ArrayList<String> adjustmentMessage) {
		this.adjustmentMessage = adjustmentMessage;
	}
}
