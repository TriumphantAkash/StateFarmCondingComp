package com.statefarm.codingcomp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import com.statefarm.codingcomp.model.Email;
import com.statefarm.codingcomp.model.User;

public class QueryServiceTest {
    private QueryService queryService;
    private SimpleDateFormat sdf;

    @Before
    public void setup() {
        queryService = new QueryServiceImpl();
        sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
    }

    /**************************** SET ONE ****************************/
    /**
     * This method should find a list of users with a given domain.
     * 
     * @return List of all users with with given domain 
     * @throws Exception
     */
    @Test
    public void testUsersByDomain() throws Exception {
        List<User> users = queryService.usersByDomain( "google.com" );
        assertNotNull( users );
        assertEquals( 4, users.size() );
    }

    /**
     * For a given date range, identify the emails that were sent in that range.
     * All times are represented in GMT.
     * 
     * Time ranges are INCLUSIVE. Time range begins at the START of date 1, and
     * ends at the END of date 2.
     * 
     * 
     * @throws Exception
     * @returns
     */
    @Test
    public void testEmailsInDateRange1() throws Exception {
        // Find all emails sent from August 2, 2014 - August 3, 2014
        List<Email> expected = Arrays.asList( new Email[] {
                new Email( "gmarshall2e@elpais.com", "dhart2e@weibo.com", sdf.parse( "2014-08-03 22:44:35" ) ),
                new Email( "aelliott2i@uol.com.br", "slawson2i@wiley.com", sdf.parse( "2014-08-03 15:57:11" ) ),
                new Email( "progers7@ftc.gov", "nnguyen7@w3.org", sdf.parse( "2014-08-03 14:42:15" ) ),
                new Email( "pwarrenv@examiner.com", "mwilsonv@arizona.edu", sdf.parse( "2014-08-03 09:45:40" ) ),
                new Email( "tfuller1c@twitpic.com", "aburton1c@reference.com", sdf.parse( "2014-08-03 07:26:05" ) ),
                new Email( "dfields1p@yahoo.com", "sweaver1p@time.com", sdf.parse( "2014-08-03 00:15:02" ) ),
                new Email( "vgibson1q@prlog.org", "jperkins1q@eventbrite.com", sdf.parse( "2014-08-02 22:34:11" ) ),
                new Email( "jharvey1s@dedecms.com", "wjones1s@privacy.gov.au", sdf.parse( "2014-08-02 22:14:19" ) ),
                new Email( "rfuller1y@princeton.edu", "cstone1y@sakura.ne.jp", sdf.parse( "2014-08-02 21:47:51" ) ),
                new Email( "sgordon6@bluehost.com", "sstevens6@netlog.com", sdf.parse( "2014-08-02 13:09:59" ) ),
                new Email( "ethomas5@imageshack.us", "lferguson5@forbes.com", sdf.parse( "2014-08-02 10:22:08" ) ),
                new Email( "dmartin1t@goodreads.com", "ehicks1t@forbes.com", sdf.parse( "2014-08-02 05:25:03" ) ) } );

        List<Email> actual = queryService.emailsInDateRange( sdf.parse( "2014-08-02 00:00:00" ), sdf.parse( "2014-08-03 23:59:59" ) );

        assertReflectionEquals( expected, actual, ReflectionComparatorMode.LENIENT_ORDER );
    }

    /**
     * For a given date range, identify the emails that were sent in that range.
     * All times are represented in GMT.
     * 
     * 
     * @throws Exception
     * @returns
     */
    @Test
    public void testEmailsInDateRange2() throws Exception {
        // Find all emails sent on April 13, 2014
        List<Email> expected = Arrays.asList( new Email[] {
                new Email( "mford1z@slashdot.org", "cmason1z@mediafire.com", sdf.parse( "2014-04-13 20:49:42" ) ),
                new Email( "jjenkins2l@privacy.gov.au", "cbishop2l@cisco.com", sdf.parse( "2014-04-13 20:48:02" ) ),
                new Email( "rwest1x@so-net.ne.jp", "lnichols1x@joomla.org", sdf.parse( "2014-04-13 10:03:12" ) ),
                new Email( "areed2m@samsung.com", "lhansen2m@issuu.com", sdf.parse( "2014-04-13 05:05:51" ) ),
                new Email( "agrahaml@networkadvertising.org", "bwagnerl@gov.uk", sdf.parse( "2014-04-13 04:36:28" ) ) } );

        List<Email> actual = queryService.emailsInDateRange( sdf.parse( "2014-04-13 00:00:00" ), sdf.parse( "2014-04-13 23:59:59" ) );

        assertReflectionEquals( expected, actual, ReflectionComparatorMode.LENIENT_ORDER );
    }

    /**
     * Group emails sent by day. All timestamps are evaluated in GMT.
     * 
     * For our test case purposes, we will spot-check a few days in the set for
     * correctness.
     * 
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void emailsByDayMayTwentySeventh() throws Exception {
        List<Email> expectedMayTwentySeventh = Arrays.asList( new Email[] {
                new Email( "bharrisons@typepad.com", "jsullivans@bbc.co.uk", sdf.parse( "2014-05-27 17:57:35" ) ),
                new Email( "tboyd27@amazon.co.uk", "tpalmer27@aboutads.info", sdf.parse( "2014-05-27 17:37:53" ) ),
                new Email( "frogers1o@rambler.ru", "lflores1o@weather.com", sdf.parse( "2014-05-27 15:50:39" ) ),
                new Email( "dwood1n@blogtalkradio.com", "tgarrett1n@intel.com", sdf.parse( "2014-05-27 13:27:50" ) ) } );

        Map<String, List<Email>> actual = queryService.emailsByDay();

        assertReflectionEquals( expectedMayTwentySeventh, actual.get( "2014-05-27" ), ReflectionComparatorMode.LENIENT_ORDER );
    }

    @Test
    public void emailsByDayJanuaryEight() throws Exception {
        List<Email> expectedJanuaryEight = Arrays.asList( new Email[] {
                new Email( "kfranklin1n@bloglines.com", "lolivero@list-manage.com", sdf.parse( "2014-01-08 23:12:50" ) ),
                new Email( "mvasquezz@slate.com", "smeyerz@google.com", sdf.parse( "2014-01-08 20:00:35" ) ),
                new Email( "jtaylora@yahoo.co.jp", "ctorresa@sphinn.com", sdf.parse( "2014-01-08 18:40:44" ) ),
                new Email( "jhughesq@youku.com", "wsullivanq@google.co.uk", sdf.parse( "2014-01-08 16:05:34" ) ),
                new Email( "rpatterson1q@epa.gov", "rperkins1q@slideshare.net", sdf.parse( "2014-01-08 06:46:12" ) ),
                new Email( "mstewart1m@scribd.com", "dwells1m@clickbank.net", sdf.parse( "2014-01-08 03:57:56" ) ),
                new Email( "jgilberts@japanpost.jp", "jcrawfords@merriam-webster.com", sdf.parse( "2014-01-08 02:19:50" ) ) } );

        Map<String, List<Email>> actual = queryService.emailsByDay();

        assertReflectionEquals( expectedJanuaryEight, actual.get( "2014-01-08" ), ReflectionComparatorMode.LENIENT_ORDER );
    }

    /**
     * For a given Date, identify how many emails were sent.
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void testEmailsOnDay1() throws Exception {
        assertEquals( 21, queryService.emailsOnDay( sdf.parse( "2014-06-18 00:00:00" ) ) );
    }

    /**
     * For a given Date, identify how many emails were sent.
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void testEmailsOnDay2() throws Exception {
        assertEquals( 16, queryService.emailsOnDay( sdf.parse( "2014-10-11 00:00:00" ) ) );
    }

    /**
     * For all of the users that are in our users.txt file, identify emails that
     * were sent FROM them. An user can send to himself.
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void testEmailsFromOurUsers() throws Exception {
        Map<User, List<Email>> expected = new HashMap<User, List<Email>>();
        expected.put(
                new User( "Kevin Nguyen", "knguyenp@youtube.com" ),
                Arrays.asList( new Email[] { new Email( "knguyenp@youtube.com", "mmeyerd@netvibes.com", sdf.parse( "2014-08-27 09:36:36" ) ),
                        new Email( "knguyenp@youtube.com", "wjonesy@sohu.com", sdf.parse( "2014-06-15 23:12:25" ) ),
                        new Email( "knguyenp@youtube.com", "jcampbell2r@symantec.com", sdf.parse( "2014-06-14 19:00:45" ) ),
                        new Email( "knguyenp@youtube.com", "tbanksa@adobe.com", sdf.parse( "2014-06-01 12:35:58" ) ),
                        new Email( "knguyenp@youtube.com", "lwoodsk@foxnews.com", sdf.parse( "2014-03-07 08:00:38" ) ),
                        new Email( "knguyenp@youtube.com", "ehanson1@sitemeter.com", sdf.parse( "2014-02-19 15:14:25" ) ) } ) );
        expected.put(
                new User( "Phillip Banks", "pbanks2l@skype.com" ),
                Arrays.asList( new Email[] { new Email( "pbanks2l@skype.com", "ryoung1a@i2i.jp", sdf.parse( "2014-07-22 10:17:48" ) ),
                        new Email( "pbanks2l@skype.com", "glynch1o@va.gov", sdf.parse( "2014-07-17 22:42:41" ) ) } ) );
        expected.put( new User( "Nicole Crawford", "ncrawfordq@google.com.br" ), Arrays.asList( new Email[] { new Email( "ncrawfordq@google.com.br",
                "ncrawfordq@google.com.br", sdf.parse( "2014-01-25 08:36:54" ) ) } ) );

        Map<User, List<Email>> actual = queryService.emailsFromOurUsers();

        assertReflectionEquals( expected, actual, ReflectionComparatorMode.LENIENT_ORDER );
    }

    /**
     * For all of the users that are in our users.txt file, identify emails that
     * were sent TO them. An user can send to himself.
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void testEmailsToOurUsers() throws Exception {
        Map<User, List<Email>> expected = new HashMap<User, List<Email>>();
        expected.put(
                new User( "Todd Shaw", "tshawd@xrea.com" ),
                Arrays.asList( new Email[] { new Email( "ahall4@bing.com", "tshawd@xrea.com", sdf.parse( "2014-12-12 16:55:58" ) ),
                        new Email( "devans21@infoseek.co.jp", "tshawd@xrea.com", sdf.parse( "2014-11-16 16:42:46" ) ) } ) );
        expected.put( new User( "Carolyn Gray", "cgray2e@nsw.gov.au" ),
                Arrays.asList( new Email[] { new Email( "eburns23@devhub.com", "cgray2e@nsw.gov.au", sdf.parse( "2014-10-15 19:36:01" ) ) } ) );
        expected.put( new User( "Angela Davis", "adavis1m@diigo.com" ),
                Arrays.asList( new Email[] { new Email( "tcarroll22@samsung.com", "adavis1m@diigo.com", sdf.parse( "2014-03-02 13:36:51" ) ) } ) );
        expected.put( new User( "Elizabeth Hanson", "ehanson1@sitemeter.com" ),
                Arrays.asList( new Email[] { new Email( "knguyenp@youtube.com", "ehanson1@sitemeter.com", sdf.parse( "2014-02-19 15:14:25" ) ) } ) );
        expected.put( new User( "Nicole Crawford", "ncrawfordq@google.com.br" ), Arrays.asList( new Email[] { new Email( "ncrawfordq@google.com.br",
                "ncrawfordq@google.com.br", sdf.parse( "2014-01-25 08:36:54" ) ) } ) );

        Map<User, List<Email>> actual = queryService.emailsToOurUsers();

        assertReflectionEquals( expected, actual, ReflectionComparatorMode.LENIENT_ORDER );
    }

    /**
     * For all of the users that are in our users.txt file, identify emails that
     * were sent FROM one of our users TO one of our users. An user can send to
     * himself.
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void testEmailsToUserFromUser() throws Exception {
        List<Email> expected = Arrays.asList( new Email[] {
                new Email( "knguyenp@youtube.com", "ehanson1@sitemeter.com", sdf.parse( "2014-02-19 15:14:25" ) ),
                new Email( "ncrawfordq@google.com.br", "ncrawfordq@google.com.br", sdf.parse( "2014-01-25 08:36:54" ) ) } );

        List<Email> actual = queryService.emailsToUserFromUser();

        assertReflectionEquals( expected, actual, ReflectionComparatorMode.LENIENT_ORDER );
    }

    /**************************** SET TWO ****************************/
    /**
     * This method should find the e-mail addresses which are connected by
     * degrees separation to email. TIP: You may want to first implement this
     * for degrees=2 before degrees>2. E.g. Bob emails Jane and Mary emails
     * Jane. Bob -> Jane <- Mary. Bob and Mary are separated by 2 degrees.
     * Degrees=1: Friends; Degrees=2: Friends of Friends; Etc.
     * 
     * @param email
     *            E-mail to use to search
     * @param degrees
     *            Number of degrees of separation to search
     * @return List of all the e-mail addresses that match
     * @throws Exception
     */

    @Test
    public void testEmailAddressesByTwoDegrees() throws Exception {
        // Emails by degrees:
        // 2 degrees: cstonec@dailymail.co.uk,hmontgomery2g@netvibes.com;
        // jmillsc@php.net,cstonec@dailymail.co.uk
        Set<String> emailAddressesByTwoDegrees = queryService.emailAddressesByDegrees( "hmontgomery2g@netvibes.com", 2 );
        assertTrue( emailAddressesByTwoDegrees.contains( "jmillsc@php.net" ) );

    }

    @Test
    public void testEmailAddressesByThreeDegrees() throws Exception {
        // 3 degrees:
        // tmeyero@walmart.com,lolivero@list-manage.com
        // kfranklin1n@bloglines.com,lolivero@list-manage.com
        // mdiaz2a@usatoday.com,kfranklin1n@bloglines.com
        Set<String> emailAddressesByThreeDegrees = queryService.emailAddressesByDegrees( "tmeyero@walmart.com", 3 );
        assertTrue( emailAddressesByThreeDegrees.contains( "mdiaz2a@usatoday.com" ) );

    }

    /**
     * List the number of degrees between emailAddressOne and emailAddressTwo.
     * Return -1 if not connected. See above test for more explanation.
     * 
     * NOTE: These tests assume a unidirectional relationship whereas the
     * previous assumed a bidirectional relationship A->B->C would be 2 degrees
     * A->B<-C would result in no connection.
     * 
     * @param emailAddressOne
     * @param emailAddressTwo
     * @return Number of degrees between emailAddressOne and emailAddressTwo. -1
     *         if not connected.
     */

    @Test
    public void testDegreesBetweenSeven() throws Exception {
        // 7 degrees (for use with the 2nd method in that section):
        // mwarrenk@columbia.edu,jmyersk@army.mil,2014-07-30T01:19:38Z
        // jmyersk@army.mil,lgrayl@nationalgeographic.com,2014-10-09T07:48:43Z
        // lgrayl@nationalgeographic.com,lwellsm@t-online.de,2014-01-02T14:21:51Z
        // lwellsm@t-online.de,npiercen@twitpic.com,2014-11-16T23:59:58Z
        // npiercen@twitpic.com,jfowlero@cloudflare.com,2014-05-30T22:39:24Z
        // jfowlero@cloudflare.com,awoodsp@columbia.edu,2014-09-30T22:43:08Z
        // awoodsp@columbia.edu,rjohnstonq@unc.edu,2014-03-28T09:45:58Z
        int degreesBetweenSeven = queryService.degreesBetween( "mwarrenk@columbia.edu", "rjohnstonq@unc.edu" );
        assertEquals( degreesBetweenSeven, 7 );

    }

    @Test
    public void testDegreesBetweenOne() throws Exception {

        int degreesBetweenOne = queryService.degreesBetween( "jrobertsoni@squidoo.com", "lthompsoni@epa.gov" );
        assertEquals( degreesBetweenOne, 1 );

    }

    @Test
    public void testNoDegreesBetween() throws Exception {

        int degreesBetween = queryService.degreesBetween( "dallen2r@bbc.co.uk", "jcampbell2r@symantec.com" );
        assertEquals( degreesBetween, -1 );

    }

    /**************************** SET THREE ****************************/

    /**
     * Finds the e-mail address that has the most number of unique connections
     * to other e-mail addresses.
     * 
     * @return
     */
    @Test
    public void testMostConnected() throws Exception {
        String mostConnectedEmail = queryService.mostConnected();
        assertEquals( mostConnectedEmail, "jperkins2k@homestead.com" );
    }

    /**
     * Finds the e-mail address that has sent the most number of e-mails.
     * 
     * @return
     */
    @Test
    public void testMostActiveSender() throws Exception {
        String mostActiveSender = queryService.mostActiveSender();
        assertEquals( mostActiveSender, "kgarza1z@ebay.co.uk" );
    }

    /**
     * Finds the e-mail address that has received the most number of e-mails.
     * 
     * @return
     */
    @Test
    public void testMostActiveReceiver() throws Exception {
        String mostActiveReceiver = queryService.mostActiveReceiver();
        assertEquals( mostActiveReceiver, "bcook1z@free.fr" );
    }

    /**
     * Finds the e-mail address that has sent the most number of e-mails to a
     * single person.
     * 
     * @return
     */
    @Test
    public void testMostSingularSender() throws Exception {
        String mostSigularSenderEmail = queryService.mostSingularSender();
        assertEquals( mostSigularSenderEmail, "kgarza1z@ebay.co.uk" );
    }

    /**
     * Finds the e-mail address that sends the most e-mails to themselves
     * (sender and receiver are the same).
     * 
     * @return
     */
    @Test
    public void testMostSelfEmails() throws Exception {
        String mostEmailsSentToSelf = queryService.mostSelfEmails();
        assertEquals( mostEmailsSentToSelf, "wreidw@slideshare.net" );
    }

    /**************************** SET FOUR ****************************/

    /**
     * Finds the most common hour during which all users to send emails. Times
     * are represented in GMT on a 0-23 hour range. In the event that multiple
     * hours are equal, the hour with the highest numerical value should be
     * used.
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void testMostPopularHourForAllUsers() throws Exception {
        int mostPopularHour = queryService.mostPopularHour();
        assertEquals( 1, mostPopularHour );
    }

    /**
     * Finds the most common hour during which a single user sends emails. Times
     * are represented in GMT on a 0-23 hour range. In the event that multiple
     * hours are equal, the hour with the highest numerical value should be
     * used.
     * 
     * @throws Exception
     */
    @Test
    public void testMostPopularHourForSingleUser1() throws Exception {
        int mostPopularHour = queryService.mostPopularHour( "kgarza1z@ebay.co.uk" );
        assertEquals( 22, mostPopularHour );
    }

    /**
     * Finds the most common hour during which a single user sends emails. Times
     * are represented in GMT on a 0-23 hour range. In the event that multiple
     * hours are equal, the hour with the highest numerical value should be
     * used.
     * 
     * @throws Exception
     */
    @Test
    public void testMostPopularHourForSingleUser2() throws Exception {
        int mostPopularHour = queryService.mostPopularHour( "jperkins2k@homestead.com" );

        assertEquals( 20, mostPopularHour );
    }

    /**
     * Finds the most common hour during which all users to send emails. Times
     * are represented in GMT on a 0-23 hour range. In the event that multiple
     * hours are equal, the hour with the highest numerical value should be
     * used.
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void testLeastPopularHourForAllUsers() throws Exception {
        int leastPopularHour = queryService.leastPopularHour();

        assertEquals( 8, leastPopularHour );
    }

    /**
     * Finds the most common hour during which a single user sends emails. Times
     * are represented in GMT on a 0-23 hour range. In the event that multiple
     * hours are equal, the hour with the highest numerical value should be
     * used. sourcetype=emails sender="kgarza1z@ebay.co.uk" | stats count by
     * date_hour | sort count -date_hour
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void testLeastPopularHourForSingleUser1() throws Exception {
        int leastPopularHour = queryService.leastPopularHour( "kgarza1z@ebay.co.uk" );

        assertEquals( 22, leastPopularHour );
    }

    /**
     * Finds the most common hour during which a single user sends emails. Times
     * are represented in GMT on a 0-23 hour range. In the event that multiple
     * hours are equal, the hour with the highest numerical value should be
     * used.
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void testLeastPopularHourForSingleUser2() throws Exception {
        int leastPopularHour = queryService.leastPopularHour( "jperkins2k@homestead.com" );

        assertEquals( 23, leastPopularHour );
    }

    /**
     * Finds the most common date on which all users sent emails Times are
     * represented in GMT on a 0-23 hour range. In the event that multiple dates
     * are equal, the most recent date should be used.
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void testMostPopularDateForAllUsers() throws Exception {
        String mostPopularDate = queryService.mostPopularDate();

        assertNotNull( mostPopularDate );
        assertEquals( "06/18/2014", mostPopularDate );
    }

    /**
     * Finds the most common date on which a single user sent emails Times are
     * represented in GMT on a 0-23 hour range. In the event that multiple dates
     * are equal, the most recent date should be used.
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void testMostPopularDateForSingleUser1() throws Exception {
        String mostPopularDate = queryService.mostPopularDate( "icox5@hostgator.com" );

        assertNotNull( mostPopularDate );
        assertEquals( "03/21/2014", mostPopularDate );
    }

    /**
     * Finds the most common date on which a single user sent emails. Times are
     * represented in GMT on a 0-23 hour range. In the event that multiple dates
     * are equal, the most recent date should be used.
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void testMostPopularDateForSingleUser2() throws Exception {
        String mostPopularDate = queryService.mostPopularDate( "knguyenp@youtube.com" );

        assertNotNull( mostPopularDate );
        assertEquals( "08/27/2014", mostPopularDate );
    }

    /**
     * Of all days that had emails sent, find the least popular across all
     * users. Times are represented in GMT on a 0-23 hour range. In the event
     * that multiple dates are equal, the most recent date should be used.
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void testLeastPopularDateForAllUsers() throws Exception {
        String leastPopularDate = queryService.leastPopularDate();

        assertNotNull( leastPopularDate );
        assertEquals( "10/27/2014", leastPopularDate );
    }

    /**
     * Of all days that a given user sent emails, find the least popular. Times
     * are represented in GMT on a 0-23 hour range. In the event that multiple
     * dates are equal, the most recent date should be used.
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void testLeastPopularDateForSingleUser1() throws Exception {
        String leastPopularDate = queryService.leastPopularDate( "wreidw@slideshare.net" );

        assertNotNull( leastPopularDate );
        assertEquals( "04/27/2014", leastPopularDate );
    }

    /**
     * Of all days that a given user sent emails, find the least popular. Times
     * are represented in GMT on a 0-23 hour range. In the event that multiple
     * dates are equal, the most recent date should be used.
     * 
     * @throws Exception
     * @return
     */
    @Test
    public void testLeastPopularDateForSingleUser2() throws Exception {
        String leastPopularDate = queryService.leastPopularDate( "kgarza1z@ebay.co.uk" );

        assertNotNull( leastPopularDate );
        assertEquals( "03/20/2014", leastPopularDate );
    }

}
