# Paging
A library to help handle simple and paging list fragments and interact with activity from fragments and items in 
Recyclerview

[ ![Download](https://api.bintray.com/packages/mohamad-khaleghy/MiddleMan/Paging/images/download.svg) ](https://bintray.com/mohamad-khaleghy/MiddleMan/Paging/_latestVersion)

## What's the problem?    
in applications with a lot of lists there is always a boilerplate code that we have to write for showing a list to the 
user:

-show initial loading    
-fetching data from the server    
-show the data in Recyclerview    
-hide initial loading     
`<paging lists when user go to the end of the list>`    
-show recycler loading     
-fetching data from the server    
-show the data in Recyclerview    

a lot of these codes are the same in each page of your application despite the how you fetch data and how you show in your Recyclerview." linear, grid, 
dividers, ..."

Here the Paging came trough with help of Middleman.        
with Paging you can just extends your fragments from BaseListFragment or BasePagingFragment
and extends your viewmodels from BaseListFragmentViewModel or BasePagingViewModel to handle 
this boiler plate codes. 

this is your recyclerview fragment:

    class HomeFragment : BasePagingFragment<HomeViewModel>() {

        override val viewModel: HomeViewModel by viewModel()

        class HomePage : PageFactory(HomeFragment::class.java) {
            override fun addToBackStack(): Boolean {
                return false
            }
    
            override fun tag(): String? {
                return this::class.java.name
            }
        }
    }

and your view model look like:


    class HomeViewModel(private val homeRepository: HomeRepository) : BasePagingViewModel<PagedList<BaseModel>>() {
    
        override fun getListingPage(vararg args: Any): Listing<PagedList<BaseModel>> {
            val location: Location = args[0] as Location
            return homeRepository.loadPage(location)
        }
    
    }

so simple Right.     

don't worry about customization of your recyclerviews and layout or time for fetching data you can always override 

    fun getRecycler(baseView: View): RecyclerView
and         

     fun getLayout(): Int

and     

     fun loadItems(vararg args: Any) 
     
functions.

in the example, you can see a full application that works with foursquare APIs.    
I strongly recommended you to look at the [middleman][1] library if you don't know it already.

[1]: https://medium.com/@MohamadKhaleghy/middleman-e9e26c0fe4a4


# install

    implementation 'com.mohamadk:paging:$latest_version'

    repositories {
        maven {
            url  "https://dl.bintray.com/mohamad-khaleghy/MiddleMan"
        }
    }