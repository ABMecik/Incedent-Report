package com.IncidentReport.web.Services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.IncidentReport.web.Listener.EMF;
import com.IncidentReport.web.Model.Message;
import com.IncidentReport.web.Model.Ticket;
import com.IncidentReport.web.Model.User;

public class MessageService {
	
	private EntityManager em;



	public MessageService() {

		em = EMF.createEntityManager();
	}



	public boolean insert(User sender, User receiver, Ticket relatedticket, String comment) {
		Message msg = new Message();
		
		try {
			em.getTransaction().begin();
			
			msg.setComment(comment);
			msg.setReceiver(receiver);
			msg.setSender(sender);
			msg.setRelatedticket(relatedticket);
			
			em.persist(msg);
			
			em.getTransaction().commit();
			em.close();
			return true;
		}
		catch(Exception e){
			em.close();
			return false;
		}
		
	}

	
	public List<Message> findRelatedMessages(Ticket ticket, int userID) {
		List<Message> messages = new ArrayList<Message>();
		
		for(Message ms : ticket.getMessages()) {
			if(ms.getReceiver().getId() == userID || ms.getSender().getId() == userID) {
				messages.add(ms);
			}
		}
		
		return messages;
	}

}
