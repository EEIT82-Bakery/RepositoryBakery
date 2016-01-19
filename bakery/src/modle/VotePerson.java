package modle;
// Generated 2016/1/18 �U�� 06:26:11 by Hibernate Tools 4.3.1

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Session;

import hibernate.util.HibernateUtil;


@Entity
@Table(name = "Vote_Person", catalog = "bakeryDB")
public class VotePerson implements java.io.Serializable {

	private VotePersonId id;
	private Member member;
	private VoteItemBean voteItem;
	private Serializable s;

	public static void main(String[] args) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			VotePerson votePerson = new VotePerson();
			VotePersonId id = new VotePersonId();
			id.setMemberId(1);
			id.setVoteItemId(9);
			votePerson.setId(id);
			session.save(votePerson);
			
			session.getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
	public VotePerson() {
	}

	public VotePerson(VotePersonId id, Member member, VoteItemBean voteItem) {
		this.id = id;
		this.member = member;
		this.voteItem = voteItem;
	}

	public VotePerson(VotePersonId id, Member member, VoteItemBean voteItem, Serializable s) {
		this.id = id;
		this.member = member;
		this.voteItem = voteItem;
		this.s = s;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "memberId", column = @Column(name = "Member_id", nullable = false) ),
			@AttributeOverride(name = "voteItemId", column = @Column(name = "Vote_item_id", nullable = false) ) })
	public VotePersonId getId() {
		return this.id;
	}

	public void setId(VotePersonId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Member_id", nullable = false, insertable = false, updatable = false)
	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Vote_item_id", nullable = false, insertable = false, updatable = false)
	public VoteItemBean getVoteItem() {
		return this.voteItem;
	}

	public void setVoteItem(VoteItemBean voteItem) {
		this.voteItem = voteItem;
	}

	@Column(name = "s")
	public Serializable getS() {
		return this.s;
	}

	public void setS(Serializable s) {
		this.s = s;
	}

}
