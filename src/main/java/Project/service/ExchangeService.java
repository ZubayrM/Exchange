package Project.service;

import Project.dto.ConversionDto;
import Project.dto.ResultConversionDto;
import Project.model.Conversion;
import Project.repositories.ConversionRepository;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Log4j2
public class ExchangeService {




    @Autowired
    private ConversionRepository conversionRepository;


    @SneakyThrows
    public ResultConversionDto conversion (ConversionDto dto){

        log.info("select: " + dto);

        StringBuilder content = new StringBuilder();
        URL url = new URL("https://www.cbr-xml-daily.ru/daily_json.js");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setConnectTimeout(0);
        urlConnection.setReadTimeout(0);

        try (final BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        } catch (final Exception ex) {
            ex.printStackTrace();

        }

        JSONObject jsonData = (JSONObject) new JSONParser().parse(content.toString());

        JSONObject valute = (JSONObject) jsonData.get("Valute");

        JSONObject o = (JSONObject) valute.get(dto.getCurrency());
        JSONObject o1 = (JSONObject) valute.get(dto.getTargetCurrency());

        double result;
        if (dto.getCurrency().equals("RUB")){
            double sum = 1 / Double.parseDouble(o1.get("Value").toString());
            result = dto.getSum() * sum ;
        } else if (dto.getTargetCurrency().equals("RUB")){
            double sum = 1 / Double.parseDouble(o.get("Value").toString());
            result = dto.getSum() * (1 / sum );
        } else {
            double sum1 = 1 / Double.parseDouble(o.get("Value").toString());
            double sum2 = 1 / Double.parseDouble(o1.get("Value").toString());
            result = dto.getSum() * (sum2 / sum1);
        }
        Conversion conversion = new Conversion();
        conversion.setIdUser(dto.getId());
        conversion.setCurrency(dto.getCurrency());
        conversion.setTargetCurrency(dto.getTargetCurrency());
        conversion.setSum(dto.getSum());
        Conversion save = conversionRepository.save(conversion);

        log.info("result: " + result);

        return new ResultConversionDto(save.getId(), result);
    }
}
