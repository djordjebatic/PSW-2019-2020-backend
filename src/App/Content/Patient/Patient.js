import React from 'react';
import { Link } from 'react-router-dom'
import "react-table/react-table.css";
import Header from '../Header/Header';
import Footer from '../Footer/Footer';

class Patient extends React.Component {
    render() {
        return(
            <div className="Patient">
                <Header/>
                <div>
                    <h1>Homepage</h1>


                </div>
                <Footer/>
            </div>

        );
    }
}

export default Patient;