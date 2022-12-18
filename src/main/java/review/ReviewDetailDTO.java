package review;

public class ReviewDetailDTO {
		private Integer review_id;
		
		private Integer writer_id;
		private String writer_nick;
		
		private Integer target_id;
		private String target_nick;
		
		private String content;
		
		private String created_at;

		public Integer getReview_id() {
			return review_id;
		}

		public void setReview_id(Integer review_id) {
			this.review_id = review_id;
		}

		public Integer getWriter_id() {
			return writer_id;
		}

		public void setWriter_id(Integer writer_id) {
			this.writer_id = writer_id;
		}

		public String getWriter_nick() {
			return writer_nick;
		}

		public void setWriter_nick(String writer_nick) {
			this.writer_nick = writer_nick;
		}

		public Integer getTarget_id() {
			return target_id;
		}

		public void setTarget_id(Integer target_id) {
			this.target_id = target_id;
		}

		public String getTarget_nick() {
			return target_nick;
		}

		public void setTarget_nick(String target_nick) {
			this.target_nick = target_nick;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getCreated_at() {
			return created_at;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}
		
		
		
}
