import com.rresino.api.com.rresino.api.data.generator.EventGenerator
import com.rresino.api.com.rresino.api.domain.MyDataSource
import spock.lang.Specification


/**
 * Created by rresino on 27/08/2016.
 */
class MyDataSourceSpec extends Specification {

    def "Validate generation of data"() {

        expect:
            dataSource.hasNext()
            dataSource.every {
                it.level != null
                it.message != null
                it.message.length() > 1
                it.timestamp != null
              //elementsSize++
            }
            //elementsSize == 10
            !dataSource.hasNext()
        where:
            dataSource = new MyDataSource(EventGenerator.generateEvents(10))
            elementsSize = 0
    }

}