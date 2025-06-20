package sms.allBoard;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sms.allBoard.Common.Domain.Member.Member;
import sms.allBoard.Common.Domain.Member.MemberRepository;

import java.util.Optional;

@SpringBootTest
class AllBoardApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(AllBoardApplicationTests.class);
	@Autowired
    private MemberRepository memberRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void test() {
		Optional<Member> member = memberRepository.findByUsername("tlsalstjr58");
		if(member.isEmpty()) {
			log.info("false");
			return;
		}
		memberRepository.delete(member.get());
		log.info("true");
	}

}
