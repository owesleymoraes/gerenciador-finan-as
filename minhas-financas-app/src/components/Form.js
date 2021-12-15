import React from 'react'
import FormGroup from './FormGroup';

class Form extends React.Component {
    render() {
        return (
            <>
                <fieldset>
                    <FormGroup label="Email *" htmlFor="exampleInputEmail1">
                    <input  type="email"
                            className="form-control" 
                            id="exampleInputEmail1" 
                            aria-describedby="emailHelp"
                            placeholder="Digite o Email"/>
                    </FormGroup>

                    <FormGroup label = "Senha *" htmlFor = "exampleInputPassword1">
                    <input  type="password"
                            className="form-control" 
                            id="exampleInputPassword1"
                            placeholder="Password"/>
                         </FormGroup>
                </fieldset>
            </>

        )
    }
}

export default Form;