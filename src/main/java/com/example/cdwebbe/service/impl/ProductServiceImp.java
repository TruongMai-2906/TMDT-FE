package com.example.cdwebbe.service.impl;

import com.example.cdwebbe.DTO.CategoryDTO;
import com.example.cdwebbe.DTO.ProductDTO;
import com.example.cdwebbe.model.Product;
import com.example.cdwebbe.payload.GetProductListOutput;
import com.example.cdwebbe.repository.ProductRepository;
import com.example.cdwebbe.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMapper modelMapper;


    /**
     * - Cấu trúc lệnh if():
     *  + name ?
     *      + name + type ?
     *          + name + type + category_keyword ?
     *              + name + type + category_keyword + price ?
     *      + name + category_keyword ?
     *          + name + category_keyword + price ?
     *      + name + price ?
     *  + type ?
     *      + type + category_keyword ?
     *          + type + category_keyword + price ?
     *      + type + price ?
     *  + category_keyword ?
     *      + category_keyword + price ?
     *  + price ?
     *
     * @param name
     * @param categoryKeyword
     * @param categoryType
     * @param price_start
     * @param price_end
     * @param pageable
     * @return
     */
    @Override
    public GetProductListOutput filter(String name, String[] categoryKeyword, String[] categoryType, double price_start, double price_end, Pageable pageable) {
        int count=0;
        List<ProductDTO> productDTOList=new ArrayList<>();
        if (name != null){ // name ?
            if (categoryType != null){ // name + type ?
                if (categoryKeyword != null){ // name + type + category_keyword ?
                    if( price_start != 0 || price_end != 100000000 ){ // name + type + category_keyword + price ?
                        System.out.println("name: " + name + ", type:"+categoryType[0]+", category_keyword: "+categoryKeyword[0]+ ", price: from"+price_start + " to " + price_end);
                        productDTOList = findByNameAndTypeAndKeywordAndPrice(name, categoryType, categoryKeyword, price_start, price_end, pageable);
                        count = productRepository.countByNameContainingIgnoreCaseAndCategoryTypeInAndCategoryKeyworkInAndPriceBetween(name, categoryType, categoryKeyword, price_start, price_end);
                    } else { // => name + type + category_keyword
                        System.out.println("name: " + name + ", type:"+categoryType[0]+", category_keyword: "+categoryKeyword[0]);
                        productDTOList = findByNameAndTypeAndKeyword(name, categoryType, categoryKeyword, pageable);
                        count = productRepository.countByNameContainingIgnoreCaseAndCategoryTypeInAndCategoryKeyworkIn(name, categoryType, categoryKeyword);
                    }
                } else { // => name + type
                    System.out.println("name: " + name + ", type:"+categoryType[0]);
                    productDTOList = findByNameAndType(name, categoryType, pageable);
                    count = productRepository.countByNameContainingIgnoreCaseAndCategoryTypeIn(name, categoryType);
                }
            } else if ( categoryKeyword != null ){ // name + category_keyword ?
                if (price_start != 0 || price_end != 100000000){ // name + category + price ?
                    System.out.println("name: " + name +", category_keyword: "+categoryKeyword[0]+ ", price: from "+price_start + " to " + price_end);
                    productDTOList = findByNameAndCategoryAndPrice(name, categoryKeyword, price_start, price_end, pageable);
                    count=productRepository.countByNameContainingIgnoreCaseAndCategoryKeyworkInAndPriceBetween(name, categoryKeyword, price_start, price_end);
                }else { // => name + category_keyword
                    System.out.println("name: " + name+", category_keyword: "+categoryKeyword[0]);
                    productDTOList = findByNameAndCategory(name, categoryKeyword, pageable);
                    count=productRepository.countByNameContainingIgnoreCaseAndCategoryKeyworkIn(name, categoryKeyword);
                }
            } else if (price_start != 0 || price_end != 100000000){ // name + price ?
                System.out.println("name: " + name + ", price: from "+price_start + " to " + price_end);
                productDTOList = findByNameAndPrice(name, price_start, price_end, pageable);
                count=productRepository.countByNameContainingIgnoreCaseAndPriceBetween(name, price_start, price_end);
            } else { // => name
                System.out.println("name: " + name);
                productDTOList = findByName(name, pageable);
                count = countByName(name);
            }
        } else if (categoryType != null) { // type ?
            if( categoryKeyword!=null ){ // type + category_keyword ?
                if( price_start != 0 || price_end != 100000000 ){ // type + category_keyword + price ?
                    System.out.println(", type:" +categoryType[0]+", category_keyword: "+ categoryKeyword[0] + ", price: from "+price_start + " to " + price_end);
                    productDTOList = findByTypeAndKeywordAndPrice(categoryType, categoryKeyword, price_start, price_end, pageable);
                    count = productRepository.countByCategoryTypeInAndCategoryKeyworkInAndPriceBetween(categoryType, categoryKeyword, price_start, price_end);
                } else { // => type + category_keyword
                    System.out.println(", type:" +categoryType[0]+", category_keyword: "+ categoryKeyword[0]);
                    productDTOList = findByTypeAndKeyword(categoryType, categoryKeyword, pageable);
                    count = productRepository.countByCategoryTypeInAndCategoryKeyworkIn(categoryType, categoryKeyword);
                }
            } else if ( price_start != 0 || price_end != 100000000 ){ // type + price ?
                System.out.println(", type:"+categoryType[0] + ", price: from "+price_start + " to " + price_end);
                productDTOList = findByTypeAndPrice(categoryType, price_start, price_end, pageable);
                count = productRepository.countByCategoryTypeInAndPriceBetween(categoryType, price_start, price_end);
            } else { // => type
                System.out.println(", type:"+categoryType[0]);
                productDTOList = findByType(categoryType, pageable);
                count = productRepository.countByCategoryTypeIn(categoryType);
            }
        } else if ( !(categoryKeyword == null) ){ //category_keyword ?
            if (price_start != 0 || price_end != 100000000){ // category_keyword + price
                System.out.println(", category_keyword: "+categoryKeyword[0]+ ", price: from "+price_start + " to " + price_end);
                productDTOList = findByCategoryAndPrice(categoryKeyword, price_start, price_end, pageable);
                count = productRepository.countByCategoryKeyworkInAndPriceBetween(categoryKeyword, price_start, price_end);
            }else { // category
                System.out.println(", category_keyword: "+categoryKeyword[0]);
                productDTOList = findByCategoryIn(categoryKeyword, pageable);
                count = productRepository.countByCategoryKeyworkIn(categoryKeyword);
            }
        } else if (price_start != 0 || price_end != 100000000){ // price ?
            System.out.println(", price: from "+price_start + " to " + price_end);
            productDTOList = findByPrice(price_start, price_end, pageable);
            count = productRepository.countByPriceBetween(price_start, price_end);
        } else {
            System.out.println("Not thing");
            productDTOList = findAll(pageable);
            count = (int) productRepository.count();
        }

        int sizeTotal=(int) Math.ceil( (double)count/pageable.getPageSize() );

        GetProductListOutput productListOutput=new GetProductListOutput();
        productListOutput.setProductDTOList(productDTOList);
        productListOutput.setSizeTotal(sizeTotal);
        return productListOutput;
    }

    /**
     * Request: Converter danh sách các ProductDTO thành ProductEntity
     * @param productList
     * @return
     */
    private List<ProductDTO> toDTO(List<Product> productList){
        List<ProductDTO> productDTOList=new ArrayList<>();
        for (Product product: productList){
            productDTOList.add(modelMapper.map(product,ProductDTO.class));
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> findAll(Pageable pageable) {
        List<Product> productList=productRepository.findAll(pageable).getContent();
        return toDTO(productList);
    }

    @Override
    public List<ProductDTO> findByName(String name, Pageable pageable) {
        List<Product> productList=productRepository.findByNameContainingIgnoreCase(name, pageable).getContent();
        return toDTO(productList);
    }


    @Override
    public List<ProductDTO> findByCategoryIn(String[] category, Pageable pageable) {
        List<Product> productList = productRepository.findAllByCategoryKeyworkIn(category, pageable).getContent();
        return toDTO(productList);
    }


    @Override
    public List<ProductDTO> findByPrice(double priceStart, double priceEnd, Pageable pageable) {
        List<Product> productList=productRepository.findAllByPriceBetween(priceStart, priceEnd, pageable).getContent();
        return toDTO(productList);
    }

    private List<ProductDTO> findByNameAndPrice(String name, double price_start, double price_end, Pageable pageable) {
        List<Product> productList = productRepository.findAllByNameContainingIgnoreCaseAndPriceBetween(name, price_start, price_end, pageable).getContent();
        return toDTO(productList);
    }

    private List<ProductDTO> findByCategoryAndPrice(String[] category, double price_start, double price_end, Pageable pageable) {
        List<Product> productList = productRepository.findAllByCategoryKeyworkInAndPriceBetween(category, price_start, price_end, pageable).getContent();
        return toDTO(productList);
    }

    private List<ProductDTO> findByNameAndCategoryAndPrice(String name, String[] category, double price_start, double price_end, Pageable pageable) {
        List<Product> productList = productRepository.findAllByNameContainingIgnoreCaseAndCategoryKeyworkInAndPriceBetween(name, category, price_start, price_end, pageable).getContent();
        return toDTO(productList);
    }

    /**
     *
     * @param name
     * @param category
     * @param pageable
     * @return
     */
    private List<ProductDTO> findByNameAndCategory(String name, String[] category, Pageable pageable) {
        List<Product> productList = productRepository.findAllByNameContainingIgnoreCaseAndCategoryKeyworkIn(name, category, pageable).getContent();
        return toDTO(productList);
    }

    //name + type + category_keyword + price
    /**
     * name + type + category_keyword + price
     * @param name
     * @param categoryType
     * @param categoryKeyword
     * @param price_start
     * @param price_end
     * @param pageable
     * @return
     */
    private List<ProductDTO> findByNameAndTypeAndKeywordAndPrice(String name, String[] categoryType, String[] categoryKeyword, double price_start, double price_end, Pageable pageable) {
        List<Product> productList = productRepository.findAllByNameContainingIgnoreCaseAndCategoryTypeInAndCategoryKeyworkInAndPriceBetween(name, categoryType, categoryKeyword, price_start, price_end, pageable).getContent();
        return toDTO(productList);
    }

    //  name + type + category_keyword ?
    /**
     * name + type + category_keyword
     * @param name
     * @param categoryType
     * @param categoryKeyword
     * @param pageable
     * @return
     */
    private List<ProductDTO> findByNameAndTypeAndKeyword(String name, String[] categoryType, String[] categoryKeyword, Pageable pageable) {
        List<Product> productList = productRepository.findAllByNameContainingIgnoreCaseAndCategoryTypeInAndCategoryKeyworkIn(name, categoryType, categoryKeyword, pageable).getContent();
        return toDTO(productList);
    }

    //  name + type ?
    /**
     * name + type
     * @param name
     * @param categoryType
     * @param pageable
     * @return
     */
    private List<ProductDTO> findByNameAndType(String name, String[] categoryType, Pageable pageable) {
        List<Product> productList = productRepository.findAllByNameContainingIgnoreCaseAndCategoryTypeIn(name, categoryType, pageable).getContent();
        return toDTO(productList);
    }

    //  type + category_keyword + price ?
    /**
     * type + category_keyword + price
     * @param categoryType
     * @param categoryKeyword
     * @param price_start
     * @param price_end
     * @param pageable
     * @return
     */
    private List<ProductDTO> findByTypeAndKeywordAndPrice(String[] categoryType, String[] categoryKeyword, double price_start, double price_end, Pageable pageable) {
        List<Product> productList = productRepository.findAllByCategoryTypeInAndCategoryKeyworkInAndPriceBetween(categoryType, categoryKeyword, price_start, price_end, pageable).getContent();
        return toDTO(productList);
    }

    //  type + category_keyword ?
    /**
     * type + category_keyword
     * @param categoryType
     * @param categoryKeyword
     * @param pageable
     * @return
     */
    private List<ProductDTO> findByTypeAndKeyword(String[] categoryType, String[] categoryKeyword, Pageable pageable) {
        List<Product> productList = productRepository.findAllByCategoryTypeInAndCategoryKeyworkIn(categoryType, categoryKeyword, pageable).getContent();
        return toDTO(productList);
    }

    //  type + price ?
    /**
     * type + price
     * @param categoryType
     * @param price_start
     * @param price_end
     * @param pageable
     * @return
     */
    private List<ProductDTO> findByTypeAndPrice(String[] categoryType, double price_start, double price_end, Pageable pageable) {
        List<Product> productList = productRepository.findAllByCategoryTypeInAndPriceBetween(categoryType, price_start, price_end, pageable).getContent();
        return toDTO(productList);
    }

    //  type ?
    /**
     * type
     * @param categoryType
     * @param pageable
     * @return
     */
    private List<ProductDTO> findByType(String[] categoryType, Pageable pageable) {
        List<Product> productList = productRepository.findAllByCategoryTypeIn(categoryType, pageable).getContent();
        return toDTO(productList);
    }


    @Override
    public int countByName(String name) {
        return (int)productRepository.countByNameContainingIgnoreCase(name);
    }
//cua Hiep
	@Override
	public Map<String, Object> listProductAdmin(Pageable pageable) {
			Page<Product> pageTuts;
//			List<ProductDTO> listProductResponse=this.findAll(pageable);
//			 Map<String, Object> result = new HashMap<>();
			pageTuts=this.productRepository.findAll(pageable);
			List<Product> listProduct=pageTuts.getContent();
			List<ProductDTO> listProductDTO=new ArrayList<ProductDTO>();
			Map<String, Object> map=new HashMap<>();
			for (Product product : listProduct) {
				ProductDTO dto=modelMapper.map(product, ProductDTO.class);
					listProductDTO.add(dto);
			}	
			map.put("products",listProductDTO);
			map.put("currentPage", pageTuts.getNumber());
			map.put("totalPages", pageTuts.getTotalPages());
			map.put("totalItems", pageTuts.getTotalElements());
			map.put("totalElement", pageTuts.getNumberOfElements());
		return map;
	}

}
