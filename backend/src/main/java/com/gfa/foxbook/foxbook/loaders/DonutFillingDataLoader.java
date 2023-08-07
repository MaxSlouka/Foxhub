package com.gfa.foxbook.foxbook.loaders;

import com.gfa.foxbook.foxbook.models.nonusermodels.DonutFilling;
import com.gfa.foxbook.foxbook.repositories.DonutFillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DonutFillingDataLoader implements CommandLineRunner {

    private final DonutFillingRepository donutFillingRepository;

    @Autowired
    public DonutFillingDataLoader(DonutFillingRepository donutFillingRepository) {
        this.donutFillingRepository = donutFillingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (donutFillingRepository.findAll().isEmpty()) {
            loadInitialDonuts();
        }
    }

    private void loadInitialDonuts() {
        DonutFilling raspberryGlazedDonut = createDonutFilling("Raspberry Glazed Donut", "Coated with raspberry-flavored glaze, offering a fruity and slightly tart taste.");
        DonutFilling pumpkinSpiceDonut = createDonutFilling("Pumpkin Spice Donut", "A seasonal favorite, often available during the fall, made with pumpkin-flavored dough and spiced with cinnamon, nutmeg, and cloves.");
        DonutFilling strawberryShortcakeDonut = createDonutFilling("Strawberry Shortcake Donut", "Inspired by the classic dessert, featuring strawberry-flavored icing and sometimes layered with whipped cream and strawberry pieces.");
        DonutFilling sMoresDonut = createDonutFilling("S/'mores Donut", "Reminiscent of the campfire treat, topped with chocolate icing, graham cracker crumbs, and toasted marshmallow.");
        DonutFilling redVelvetDonut = createDonutFilling("Red Velvet Donut", "With a red velvet cake flavor, often topped with cream cheese icing and red velvet cake crumbs.");
        DonutFilling caramelAppleDonut = createDonutFilling("Caramel Apple Donut", "Coated with caramel icing and often drizzled with caramel sauce, capturing the flavors of a caramel apple.");
        DonutFilling churroDonut = createDonutFilling("Churro Donut", "Shaped and flavored like a churro, often dusted with cinnamon and sugar for a sweet and spiced taste.");
        DonutFilling peanutButterDonut = createDonutFilling("Peanut Butter Donut", "Infused with peanut butter flavor, sometimes topped with peanut butter icing and crushed peanuts.");
        DonutFilling matchaGreenTeaDonut = createDonutFilling("Matcha Green Tea Donut", "Made with matcha (green tea) flavored dough, often coated with matcha-flavored glaze or icing.");
        DonutFilling blackberryFilledDonut = createDonutFilling("Blackberry-Filled Donut", "Filled with blackberry jam or filling, offering a unique and fruity twist.");
        DonutFilling mochaDonut = createDonutFilling("Mocha Donut", "A combination of chocolate and coffee flavors, often featuring mocha-flavored icing.");
        DonutFilling saltedCaramelDonut = createDonutFilling("Salted Caramel Donut", "Topped with a salted caramel glaze or drizzle, balancing sweet and salty flavors.");
        DonutFilling honeyGlazedDonut = createDonutFilling("Honey Glazed Donut", "Coated with a glaze made from honey, offering a distinct and natural sweetness.");
        DonutFilling coconutCreamDonut = createDonutFilling("Coconut Cream Donut", "Filled with coconut-flavored cream or custard and sometimes topped with shredded coconut.");
        DonutFilling bananaCreamDonut = createDonutFilling("Banana Cream Donut", "Filled with banana-flavored cream or custard, often topped with icing and banana slices.");
        DonutFilling keyLimeDonut = createDonutFilling("Key Lime Donut", "Infused with key lime flavor, often topped with key lime-flavored icing or glaze.");
        DonutFilling marbleDonut = createDonutFilling("Marble Donut", "With a swirled pattern of chocolate and vanilla flavors, offering a visually appealing and delicious combination.");
        DonutFilling pecanPieDonut = createDonutFilling("Pecan Pie Donut", "Inspired by the flavors of pecan pie, often topped with pecan pieces and caramel drizzle.");
        DonutFilling glazedDonut = createDonutFilling("Glazed Donut", "A classic donut coated with a sweet, translucent glaze that adds a slightly crispy texture and sugary flavor.");
        DonutFilling chocolateDonut = createDonutFilling("Chocolate Donut", "Made with chocolate-flavored dough, often topped with chocolate icing or drizzled with chocolate sauce.");
        DonutFilling jellyFilledDonut = createDonutFilling("Jelly-Filled Donut", "Round donut filled with fruit jelly or jam, typically dusted with powdered sugar.");
        DonutFilling creamFilledDonut = createDonutFilling("Cream-Filled Donut", "Similar to a jelly-filled donut, but filled with sweet cream or custard instead of jelly.");
        DonutFilling bostonCreamDonut = createDonutFilling("Boston Cream Donut", "Yeast-raised donut filled with vanilla custard and topped with chocolate glaze.");
        DonutFilling powderedSugarDonut = createDonutFilling("Powdered Sugar Donut", "A simple yeast or cake donut coated with powdered sugar, leaving a light, powdery texture.");
        DonutFilling cinnamonSugarDonut = createDonutFilling("Cinnamon Sugar Donut", "Coated with a mixture of cinnamon and sugar, often with a warm and comforting flavor.");
        DonutFilling oldFashionedDonut = createDonutFilling("Old-Fashioned Donut", "A cake-like donut with a slightly crunchy exterior and a tender, flavorful interior. It may have a hint of nutmeg or other spices.");
        DonutFilling blueberryDonut = createDonutFilling("Blueberry Donut", "Flavored with blueberries and often containing actual blueberry pieces, topped with glaze or sugar.");
        DonutFilling mapleBar = createDonutFilling("Maple Bar", "A rectangular-shaped donut with maple-flavored icing or glaze on top.");
        DonutFilling appleFritter = createDonutFilling("Apple Fritter", "Deep-fried donut containing chunks of apple and often spiced with cinnamon, coated with glaze or powdered sugar.");
        DonutFilling coconutDonut = createDonutFilling("Coconut Donut", "Coated with shredded coconut, sometimes with coconut-flavored icing.");
        DonutFilling sprinkleDonut = createDonutFilling("Sprinkle Donut", "A colorful donut covered in rainbow or chocolate sprinkles, offering a fun and festive appearance.");
        DonutFilling raspberryFilledDonut = createDonutFilling("Raspberry-Filled Donut", "Filled with raspberry preserves or jam and often topped with icing.");
        DonutFilling lemonFilledDonut = createDonutFilling("Lemon-Filled Donut", "Filled with tangy lemon-flavored filling, typically dusted with powdered sugar.");
        DonutFilling strawberryFilledDonut = createDonutFilling("Strawberry-Filled Donut", "Filled with strawberry jam or filling, often topped with strawberry-flavored icing.");

        DonutFilling[] donutFillings = {raspberryGlazedDonut, pumpkinSpiceDonut,
                strawberryShortcakeDonut, sMoresDonut, redVelvetDonut,
                caramelAppleDonut, churroDonut, peanutButterDonut,
                matchaGreenTeaDonut, blackberryFilledDonut, mochaDonut,
                saltedCaramelDonut, honeyGlazedDonut, coconutCreamDonut,
                bananaCreamDonut, keyLimeDonut, marbleDonut, pecanPieDonut,
                glazedDonut, chocolateDonut, jellyFilledDonut, creamFilledDonut,
                bostonCreamDonut, powderedSugarDonut, cinnamonSugarDonut,
                oldFashionedDonut, blueberryDonut, mapleBar, appleFritter,
                coconutDonut, sprinkleDonut, raspberryFilledDonut, lemonFilledDonut,
                strawberryFilledDonut};
        donutFillingRepository.saveAll(Arrays.asList(donutFillings));
    }

    private DonutFilling createDonutFilling(String base, String topping) {
        DonutFilling donutFilling = new DonutFilling();
        donutFilling.setBase(base);
        donutFilling.setTopping(topping);
        return donutFilling;
    }
}
