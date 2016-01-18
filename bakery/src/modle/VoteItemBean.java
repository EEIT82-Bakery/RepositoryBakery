package modle;
// Generated 2016/1/18 �U�� 06:26:11 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;

import hibernate.util.HibernateUtil;

@Entity
@Table(name = "Vote_item", catalog = "bakeryDB")
public class VoteItemBean implements java.io.Serializable {

	private int voteItemId;
	private String voteItemName;
	private byte[] voteItemPhoto;
	private Integer voteId;
	private Set<VotePerson> votePersons = new HashSet<VotePerson>(0);

	public static void main(String[] args){
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			VoteItemBean voteItem = new VoteItemBean();
			voteItem.setVoteItemName("選項5");
			voteItem.setVoteItemPhoto(null);
			voteItem.setVoteId(2);
			VotePerson votePerson = new VotePerson();
			session.getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
	
	
	public VoteItemBean() {
	}

	public VoteItemBean(int voteItemId) {
		this.voteItemId = voteItemId;
	}

	public VoteItemBean(int voteItemId, String voteItemName, byte[] voteItemPhoto, Integer voteId,
			Set<VotePerson> votePersons) {
		this.voteItemId = voteItemId;
		this.voteItemName = voteItemName;
		this.voteItemPhoto = voteItemPhoto;
		this.voteId = voteId;
		this.votePersons = votePersons;
	}

	@Id
	@Column(name = "Vote_item_id", unique = true, nullable = false)
	public int getVoteItemId() {
		return this.voteItemId;
	}

	public void setVoteItemId(int voteItemId) {
		this.voteItemId = voteItemId;
	}

	@Column(name = "Vote_item_name", length = 50)
	public String getVoteItemName() {
		return this.voteItemName;
	}

	public void setVoteItemName(String voteItemName) {
		this.voteItemName = voteItemName;
	}

	@Column(name = "Vote_item_photo")
	public byte[] getVoteItemPhoto() {
		return this.voteItemPhoto;
	}

	public void setVoteItemPhoto(byte[] voteItemPhoto) {
		this.voteItemPhoto = voteItemPhoto;
	}

	@Column(name = "Vote_id")
	public Integer getVoteId() {
		return this.voteId;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "voteItem")
	public Set<VotePerson> getVotePersons() {
		return this.votePersons;
	}

	public void setVotePersons(Set<VotePerson> votePersons) {
		this.votePersons = votePersons;
	}

}
