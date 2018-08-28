package org.mycompany.mb;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.mycompany.common.Book;
import org.mycompany.services.BooksService;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component(value="bookMB")
@ViewScoped
public class BookMB implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Autowired
	private BooksService BookService;

	private Book bean;
	private Book beanSelected;
	private List<Book> list;
	private List<Book> listSelected;
	
	@PostConstruct
    public void init() {
		refreshList();
    }



	public void refreshList() {
		this.setBean(new Book());
		this.setBeanSelected(new Book());
		this.list = new ArrayList<Book>();
		this.listSelected = new ArrayList<Book>();
		try {
			this.list.addAll(BookService.findAll());
			this.listSelected.addAll(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public void save() {
		try {
		    
			BookService.save(this.getBean());
			refreshList();
			showSuccessMessage("persist item");
			this.setBean(new Book());
		} catch (Exception e) {
			showErrorMessage(e,"persist item");
			e.printStackTrace();
		}
	}

	public void update() {
		try {
			BookService.update(this.getBeanSelected());
			refreshList();
			showSuccessMessage("update item");
		} catch (Exception e) {
			showErrorMessage(e,"update item");
		}
	}

	public void delete() {
		try {
			BookService.delete(this.getBeanSelected().getId());
			refreshList();
			showSuccessMessage("delete item");
		} catch (Exception e) {
			showErrorMessage(e,"delete item");
		}
	}

	public void onCancel(RowEditEvent event) {
		refreshList();
	}

	
	public void reset() {
		refreshList();
        RequestContext.getCurrentInstance().reset("form1:panel");  
	}

	
	public void showSuccessMessage(String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Operation "+operation+" success");
		FacesMessage msg = null;  
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Success"); 
		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}


	public void showErrorMessage(Exception e, String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.ERROR, "Operation "+operation+" Error ",e);
		FacesMessage msg = null;  
		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Message", "error happen");  
		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}


	public List<Book> getList() {
		if(list == null){
			list = new ArrayList<Book>();
		}
		return list;
	}

	public void setList(List<Book> list) {
		this.list = list;
	}

	public List<Book> getListSelected() {
		return listSelected;
	}

	public void setListSelected(List<Book> listSelected) {
		this.listSelected = listSelected;
	}



	public Book getBean() {
		return bean;
	}



	public void setBean(Book bean) {
		this.bean = bean;
	}



	public Book getBeanSelected() {
		return beanSelected;
	}



	public void setBeanSelected(Book beanSelected) {
		this.beanSelected = beanSelected;
	}

	
	
	
	
}
