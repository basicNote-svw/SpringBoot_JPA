package com.example.spring_shop.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
//@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @Autowired
    ItemController(ItemRepository itemRepository, ItemService itemService) {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    @GetMapping("/list")
    String list(Model model) {
//        List<Item> result = itemRepository.findAll();
        List<Item> result = itemService.getAllItems();
//        System.out.println(result); // [com.example.spring_shop.item.Item@6e8e6f05, com.example.spring_shop.item.Item@679ad4a3, com.example.spring_shop.item.Item@6cf1174]
//        System.out.println(result.get(0));          // com.example.spring_shop.item.Item@6f2a15fe
//        System.out.println(result.get(0).title);    // 셔츠
//        System.out.println(result.get(0).getTitle());   // 셔츠
//        model.addAttribute("name", "롱스커트");
        model.addAttribute("items", result);
        return "redirect:/list/page/1";
    }

    @GetMapping("/list/page/{no}")
    String getListPage(Model model, @PathVariable Integer no) {
        Page<Item> result = itemRepository.findPageBy(PageRequest.of(no-1, 5));
//        model.addAttribute("items", result);
        System.out.println(result.getTotalPages());
        System.out.println(result.hasNext());
        int totalPages = result.getTotalPages();
        if (totalPages == 0) totalPages = 1;

        model.addAttribute("items", result.getContent());
        model.addAttribute("totalPages", totalPages);
        return "list";
    }

//    // Lombok 사용 X 시
//    @Autowired
//    ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) {
//        Optional<Item> result = itemRepository.findById(id);
//        if(result.isPresent()) {
//            System.out.println(result.get());
//            model.addAttribute("data", result.get());
//            return "detail";
//        } else {
//            throw new ResponseStatusException(
//                HttpStatus.NOT_FOUND, "404 에러 발생"
//            );
//        }
        Item item = itemService.getItemById(id);
//        System.out.println(item);
        model.addAttribute("data", item);
        return "detail";
    }

    @GetMapping("/write")
    String write() {
        return "write";
    }

    @PostMapping("/add")
    @CrossOrigin
        // 방법1
//    String writePost(@RequestParam String title, @RequestParam Integer price) {
//        System.out.println(title + " " + price);
//        Item item = new Item();
//        item.setTitle(title);
//        item.setPrice(price);
//        itemRepository.save(item);
//        return "redirect:/list";
//    }
        // 방법2
//    String writePost(@RequestParam Map formData) {
////        var test = new HashMap<>();
//        HashMap<String, Object> test = new HashMap<>();
//        test.put("title", "모자");
//        test.put("price", 10000);
//        System.out.println(test);   // {price=10000, title=모자}
//        System.out.println(test.get("title"));  // 모자
//        return "redirect:/list";
//    }
        // 방법3
//    String writePost(@ModelAttribute Item item) {
//        itemRepository.save(item);
//        return "redirect:/list";
//    }
    String writePost(String title, Integer price) {
        itemService.saveItem(title, price);
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Long id, Model model) {
        Optional<Item> result = Optional.ofNullable(itemService.getItemById(id));
        if(result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit";
        } else {
            return "redirect:/list";
        }
    }

    @PostMapping("/edit")
    String editItem(String title, Integer price, Long id) {
        itemService.editItem(title, price, id);
        return "redirect:/list";
    }

//    @PostMapping("/test1")
//    String test1(@RequestBody Map<String, Object> body) {
//        System.out.println(body.get("title"));
//        return "redirect:/list";
//    }

    @PostMapping("/test1")
    String test(@RequestBody Item body) {
        System.out.println(body);
        return "redirect:/list";
    }

    @DeleteMapping("/item")
    ResponseEntity<String> deleteItem(@RequestParam Long id) {
        itemRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }
}

