package add.haslearntit.domain

import add.haslearntit.domain.entry.EntryRepository
import add.haslearntit.hooks.Context


class Domain{
        
    public <T> T getBean(Class<T> beanClass){
        return Context.get().getBean(beanClass);
    } 
    
}
