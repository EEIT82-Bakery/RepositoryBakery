package modle;
// Generated 2016/1/18 �U�� 06:26:11 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class VotePersonId implements java.io.Serializable {

	private int memberId;
	private int voteItemId;
	public VotePersonId() {
	}

	public VotePersonId(int memberId, int voteItemId) {
		this.memberId = memberId;
		this.voteItemId = voteItemId;
	}

	@Column(name = "Member_id", nullable = false)
	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Column(name = "Vote_item_id", nullable = false)
	public int getVoteItemId() {
		return this.voteItemId;
	}

	public void setVoteItemId(int voteItemId) {
		this.voteItemId = voteItemId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VotePersonId))
			return false;
		VotePersonId castOther = (VotePersonId) other;

		return (this.getMemberId() == castOther.getMemberId()) && (this.getVoteItemId() == castOther.getVoteItemId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getMemberId();
		result = 37 * result + this.getVoteItemId();
		return result;
	}

}
