/**
 *
 */
package ua.store.tag;

import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.store.model.entity.User;
import ua.store.service.UserService;

/**
 * @author Sergey
 *
 */
@Service
@Transactional
public class UserList {
	
	private static final Logger logger = LogManager.getLogger(UserList.class);

	private TreeSet<User> userList;
	private Iterator<User> iterator;
	private User user;
	
	public void createUserList(List<User> users) {

		logger.debug("UserList - createUserList() - begin");

		// get list of all users
		TreeSet<User> userList = takeUserList(users);
		
		this.userList = userList;
		this.iterator = userList.iterator();
	}

	private TreeSet<User> takeUserList(List<User> users) {
		TreeSet<User> userTreeSet = new TreeSet<>();
		for (User user : users) {
			userTreeSet.add(user);
		}
		return userTreeSet;
	}

	public void resetIterator() {
		this.iterator = userList.iterator();
	}

	public int getSize() {
		return this.userList.size();
	}

	public String getUserId() {
		if (iterator.hasNext()) {
			this.user = iterator.next();
			return String.valueOf(user.getId());
		} else {
			return null;
		}
	}

	public String getUserName() {
		if (user != null) {
			return this.user.getName();
		} else {
			return null;
		}
	}

	public String getFirstName() {
		if (user != null) {
			return this.user.getFirstName();
		} else {
			return null;
		}
	}

	public String getLastName() {
		if (user != null) {
			return this.user.getLastName();
		} else {
			return null;
		}
	}

	public String getUserType() {
		if (user != null) {
			return this.user.getUserType();
		} else {
			return null;
		}
	}

	public String getEmail() {
		if (user != null) {
			return this.user.getEmail();
		} else {
			return null;
		}
	}

	public String getPhone() {
		if (user != null) {
			return this.user.getPhone();
		} else {
			return null;
		}
	}

	public String getAddress() {
		if (user != null) {
			return this.user.getAddress();
		} else {
			return null;
		}
	}

	public String getComments() {
		if (user != null) {
			return this.user.getComments();
		} else {
			return null;
		}
	}

	public String getInBlackList() {
		if (user != null) {
			return String.valueOf(this.user.isInBlackList());
		} else {
			return null;
		}
	}


	/**
	 * @return the userList
	 */
	public TreeSet<User> getUserList() {
		return userList;
	}

	public String toString() {
		String str = "";
		for (User user : this.userList) {
			str += user.getName() + " ";
		}

		return str;
	}

}
